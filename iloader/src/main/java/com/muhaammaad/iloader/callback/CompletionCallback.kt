package com.muhaammaad.iloader.callback

/**
 * A contract to tell task completion status and data
 */
interface CompletionCallback<V, A> {
    fun completed(response: V, isSuccess: A)
}
