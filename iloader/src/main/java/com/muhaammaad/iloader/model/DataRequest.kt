package com.muhaammaad.iloader.model

import okhttp3.Headers
import okhttp3.RequestBody
import okhttp3.internal.EMPTY_HEADERS

/**
 * A model handling requests
 */
open class DataRequest(
    val url: String = "", val method: String = "GET",
    val requestBody: RequestBody? = null, val headers: Headers = EMPTY_HEADERS
)