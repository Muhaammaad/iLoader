package com.muhaammaad.iloader

import com.muhaammaad.iloader.util.putValue
import com.muhaammaad.iloader.memory.MemoryCache
import org.junit.After
import org.junit.Before
import org.junit.Test

class LruCacheTest {
    private lateinit var cache: MemoryCache

    @Before
    fun setUp() {
        cache = MemoryCache(MaxSize)
    }

    @After
    fun tearDown() {
        cache.clearMemory()
    }

    @Test
    fun insertAndFetch() {

        /**
         * String les than max memory is inserted and fetched
         */
        cache.putValue(fourBytesString, fourBytesString.toByteArray())
        assert(cache.get(fourBytesString) != null)
        assert(fourBytesString.toByteArray().contentEquals(cache.get(fourBytesString)!!.byteArray))

        /**
         * String equivalent to max memory is inserted and fetched
         */
        cache.putValue(EightBytesString, EightBytesString.toByteArray())
        assert(cache.get(EightBytesString) != null)
        assert(EightBytesString.toByteArray().contentEquals(cache.get(EightBytesString)!!.byteArray))

        /**
         * String equivalent to max memory was inserted so older values were removed
         */
        assert(cache.get(fourBytesString) == null)
    }

    companion object {
        private const val fourBytesString = "Test"
        private const val EightBytesString = "LongTest"
        private val MaxSize = EightBytesString.count()
    }
}