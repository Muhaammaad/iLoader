package com.muhaammaad.iloader.memory

import android.content.ComponentCallbacks2
import androidx.collection.LruCache

/** A memory cache for [ByteArray]s. */
internal interface MemoryCache {

    companion object {
        operator fun invoke(
            maxSize: Int
        ): MemoryCache {
            return if (maxSize > 0) {
                RealMemoryCache(maxSize)
            } else {
                EmptyMemoryCache
            }
        }
    }

    fun get(key: String): Value?

    fun set(key: String, value: ByteArray, isSampled: Boolean)

    fun size(): Int

    fun maxSize(): Int

    fun clearMemory()

    fun trimMemory(level: Int)


}

data class Value(
    val byteArray: ByteArray,
    val isSampled: Boolean,
    val size: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Value

        if (!byteArray.contentEquals(other.byteArray)) return false
        if (isSampled != other.isSampled) return false
        if (size != other.size) return false

        return true
    }

    override fun hashCode(): Int {
        var result = byteArray.contentHashCode()
        result = 31 * result + isSampled.hashCode()
        result = 31 * result + size
        return result
    }
}

/** A [MemoryCache] implementation that caches nothing. */
private object EmptyMemoryCache : MemoryCache {

    override fun get(key: String): Value? = null

    override fun set(key: String, value: ByteArray, isSampled: Boolean) {}

    override fun size(): Int = 0

    override fun maxSize(): Int = 0

    override fun clearMemory() {}

    override fun trimMemory(level: Int) {}
}

/** A [MemoryCache] implementation backed by an [LruCache]. */
private class RealMemoryCache(
    maxSize: Int
) : MemoryCache {

    companion object {
        private const val TAG = "RealMemoryCache"
    }

    private val cache = object : LruCache<String, Value>(maxSize) {
        override fun sizeOf(key: String, value: Value) = value.size
    }

    override fun get(key: String): Value? = cache.get(key)

    override fun set(key: String, value: ByteArray, isSampled: Boolean) {
        // If the bitmap is too big for the cache, don't even attempt to store it. Doing so will cause
        // the cache to be cleared. Instead just evict an existing element with the same key if it exists.
        val size = value.count()
        if (size > maxSize()) {
            cache.remove(key)
            return
        }

        cache.put(key, Value(value, isSampled, size))
    }

    override fun size(): Int = cache.size()

    override fun maxSize(): Int = cache.maxSize()

    override fun clearMemory() {
        cache.trimToSize(-1)
    }

    override fun trimMemory(level: Int) {
        if (level >= ComponentCallbacks2.TRIM_MEMORY_BACKGROUND) {
            clearMemory()
        } else if (level in ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW until ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            cache.trimToSize(size() / 2)
        }
    }
}
