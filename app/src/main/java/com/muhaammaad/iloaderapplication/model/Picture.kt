package com.muhaammaad.iloaderapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Picture Models returned from PasteBin Api
 */
data class Picture(

    @SerializedName("id")
    @Expose
    var id: String = "",
    @SerializedName("created_at")
    @Expose
    var createdAt: String = "",
    @SerializedName("width")
    @Expose
    var width: Int = 0,
    @SerializedName("height")
    @Expose
    var height: Int = 0,
    @SerializedName("color")
    @Expose
    var color: String = "",
    @SerializedName("likes")
    @Expose
    var likes: Int = 0,
    @SerializedName("liked_by_user")
    @Expose
    var likedByUser: Boolean = false,
    @SerializedName("user")
    @Expose
    var user: User = User(),
    @SerializedName("current_user_collections")
    @Expose
    var currentUserCollections: ArrayList<Any> = ArrayList(),
    @SerializedName("urls")
    @Expose
    var urls: Urls = Urls(),
    @SerializedName("categories")
    @Expose
    var categories: ArrayList<Category> = ArrayList(),
    @SerializedName("links")
    @Expose
    var links: Links = Links()
) : Serializable


data class Category(

    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("title")
    @Expose
    var title: String = "",
    @SerializedName("photo_count")
    @Expose
    var photoCount: Int = 0,
    @SerializedName("links")
    @Expose
    var links: Links = Links()
) : Serializable


data class Links(

    @SerializedName("self")
    @Expose
    var self: String = "",
    @SerializedName("html")
    @Expose
    var html: String = "",
    @SerializedName("photos")
    @Expose
    var photos: String = "",
    @SerializedName("likes")
    @Expose
    var likes: String = ""
) : Serializable


data class ProfileImage(

    @SerializedName("small")
    @Expose
    var small: String = "",
    @SerializedName("medium")
    @Expose
    var medium: String = "",
    @SerializedName("large")
    @Expose
    var large: String = ""
) : Serializable


data class User(
    @SerializedName("id")
    @Expose
    var id: String = "",
    @SerializedName("username")
    @Expose
    var username: String = "",
    @SerializedName("name")
    @Expose
    var name: String = "",
    @SerializedName("profile_image")
    @Expose
    var profileImage: ProfileImage = ProfileImage(),
    @SerializedName("links")
    @Expose
    var links: Links = Links()
) : Serializable

data class Urls(

    @SerializedName("raw")
    @Expose
    var raw: String = "",
    @SerializedName("full")
    @Expose
    var full: String = "",
    @SerializedName("regular")
    @Expose
    var regular: String = "",
    @SerializedName("small")
    @Expose
    var small: String = "",
    @SerializedName("thumb")
    @Expose
    var thumb: String = ""
) : Serializable