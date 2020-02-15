package com.muhaammaad.iloader.contract.implementation

import com.muhaammaad.iloader.contract.DataContract
import com.muhaammaad.iloader.memory.MemoryCache
import com.muhaammaad.iloader.util.getValue
import com.muhaammaad.iloader.util.putValue

/**
 * A singleton that holds the default [MemoryCache] instance.
 * Responsible to provide data from cache
 */
internal object CacheContractImp : DataContract {
    private val maxMemory = (Runtime.getRuntime().maxMemory()).toInt()
    private val memoryCache = MemoryCache(maxMemory / 8)

    override suspend fun <T> getData(param: T): Any? {
        return (param as? String)?.let {
            memoryCache.getValue(it)
        }
    }

    fun putValue(url: String, bytes: ByteArray?, isSampled: Boolean) {
        memoryCache.putValue(url, bytes ?: ByteArray(0), isSampled)
    }
}
