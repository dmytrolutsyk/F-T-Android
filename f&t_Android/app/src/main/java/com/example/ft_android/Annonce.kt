package com.example.ft_android

import com.google.gson.annotations.SerializedName

class Annonce {
    @SerializedName("userID")
    public var userID: String? = null

    @SerializedName("title")
    public var title: String? = null

    @SerializedName("description")
    public var description: String? = null

    @SerializedName("category")
    public var category: String? = null

    @SerializedName("type")
    public var type: String? = null

    @SerializedName("photos")
    public var photos: String? = null

    @SerializedName("createdAt")
    public var createdAt: String? = null

    @SerializedName("lastUpdatedAt")
    public var lastUpdatedAt: String? = null

    @SerializedName("_id")
    public var _id: String? = null

    constructor(userID: String, title: String, description: String, category: String, type: String, photos: String, createdAt: String, lastUpdatedAt: String, _id: String){
        this.userID = userID
        this.title = title
        this.description = description
        this.category = category
        this.type = type
        this.photos = photos
        this.createdAt = createdAt
        this.lastUpdatedAt = lastUpdatedAt
        this._id = _id
    }




}