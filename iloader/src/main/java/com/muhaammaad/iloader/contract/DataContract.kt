package com.muhaammaad.iloader.contract

/**
 * A contract to get data
 */
internal interface DataContract {
    suspend fun <T> getData(param: T): Any?
}