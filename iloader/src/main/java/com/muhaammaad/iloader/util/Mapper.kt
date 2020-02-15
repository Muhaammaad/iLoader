package com.muhaammaad.iloader.util

import com.muhaammaad.iloader.contract.implementation.RemoteContractImp

/**
 * An interface to convert data with type [T] into [V].
 *
 * Use this to map custom data types to a type that can be handled by a [RemoteContractImp].
 */
interface Mapper<T : Any?, V : Any> {
    /** Convert [data] into [V]. */
    fun map(data: T): V
}
