package com.muhaammaad.iloader.contract.implementation

import com.muhaammaad.iloader.contract.DataContract
import com.muhaammaad.iloader.model.DataRequest
import com.muhaammaad.iloader.util.await
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.net.URL
import java.util.concurrent.TimeUnit


/**
 * A singleton that holds the default [OkHttpClient] instance.
 * Responsible to provide data from Remote
 */
internal object RemoteContractImp :
    DataContract {
    private const val TIMEOUT_SECS = 15

    override suspend fun <T> getData(param: T): Any? {
        return try {
            (param as? DataRequest)?.let {
                val request = Request.Builder().url(URL(it.url)).headers(it.headers)
                    .method(it.method, it.requestBody)
                val response = OkHttpClient.Builder()
                    .callTimeout(TIMEOUT_SECS.toLong(), TimeUnit.SECONDS)
                    .build().newCall(request.build()).await()
                if (!response.isSuccessful) {
                    throw HttpException(
                        response
                    )
                }
                val body = checkNotNull(response.body)
                body.source()
            }
        } catch (e: Exception) {
            null
        }
    }

}

internal data class HttpException(val response: Response) :
    RuntimeException("HTTP ${response.code}: ${response.message}")
