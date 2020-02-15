package com.muhaammaad.iloader.contract.implementation

import com.muhaammaad.iloader.contract.DataContract
import com.muhaammaad.iloader.model.DataRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import okio.BufferedSource

/**
 * A singleton that finds and provides the data from cache and remote.
 */
internal object DataContractImp : DataContract {

    override suspend fun <T> getData(param: T): Any? = withContext(Dispatchers.IO) {
        var baseResult: ByteArray? = null
        getRequestData(
            param
        )?.let { request ->
            baseResult = CacheContractImp.getData(
                request.url
            )?.let {
                (it as? ByteArray)
            } ?: kotlin.run {
                try {
                    RemoteContractImp.getData(
                        request
                    )?.let { (it as? BufferedSource)?.readByteArray() }
                } catch (e: Exception) {
                    null
                }
            }?.also {
                CacheContractImp.putValue(
                    request.url,
                    it,
                    false
                )
            }
        }
        // Check if we're cancelled.
        ensureActive()
        return@withContext baseResult
    }

    private fun <T> getRequestData(source: T): DataRequest? {
        return when (source) {
            is DataRequest -> {
                source
            }
            is String -> {
                DataRequest(source)
            }
            else -> null
        }
    }
}