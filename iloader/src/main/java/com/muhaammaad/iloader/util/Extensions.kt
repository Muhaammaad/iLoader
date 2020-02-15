package com.muhaammaad.iloader.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.muhaammaad.iloader.callback.ContinuationCallback
import com.muhaammaad.iloader.memory.MemoryCache
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Response


/**
 * Extensions for [Call]
 */
//region CALL Extensions
internal suspend inline fun Call.await(): Response {
    return suspendCancellableCoroutine { continuation ->
        val callback = ContinuationCallback(
            this,
            continuation
        )
        enqueue(callback)
        continuation.invokeOnCancellation(callback)
    }
}
//endregion

/**
 * Extensions for [ByteArray]
 */
//region ByteArray Extensions
internal fun ByteArray.withNotNullNorEmpty(): ByteArray? {
    if (this.isNotEmpty()) {
        return this
    }
    return null
}

internal fun ByteArray.decodeToBitmap(): Bitmap = BitmapFactory.decodeByteArray(
    this,
    0,
    this.size,
    BitmapFactory.Options().apply {
        //TODO: Needs decent handling for bitmap decoding
        // inSampleSize = 2
        inJustDecodeBounds = false
    })
//endregion

/**
 * Extensions for [MemoryCache]
 */
//region ByteArray Extensions
internal fun MemoryCache.getValue(key: String?): ByteArray? {
    return key?.let { get(it)?.byteArray }
}

internal fun MemoryCache.putValue(key: String?, value: ByteArray, isSampled: Boolean = false) {
    if (key != null) {
        set(key, value, isSampled)
    }
}
//endregion
