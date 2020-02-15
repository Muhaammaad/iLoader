package com.muhaammaad.iloaderapplication.mapper

import com.google.gson.Gson
import com.muhaammaad.iloader.util.Mapper
import com.muhaammaad.iloaderapplication.model.Picture

/**
 * Mapping Helper for [Picture] List
 * Maps ByteArray to Picture list using [Mapper] into [PictureListMapper]
 */
open class PictureListMapper : Mapper<ByteArray, List<Picture>> {
    override fun map(data: ByteArray): List<Picture> {
        return Gson().fromJson(
            String(data),
            Array<Picture>::class.java
        ).toList()
    }
}