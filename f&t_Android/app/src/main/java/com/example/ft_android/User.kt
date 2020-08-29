package com.example.ft_android

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("username")
    public var username: String? = null

    @SerializedName("password")
    public var password: String? = null

    @SerializedName("ville")
    public var ville: String? = null

    @SerializedName("email")
    public var email: String? = null

    @SerializedName("status_user")
    public var status_user: String? = null

    @SerializedName("phone")
    public var phone: String? = null

    @SerializedName("createdAt")
    public var createdAt: String? = null

    @SerializedName("lastUpdatedAt")
    public var lastUpdatedAt: String? = null

    @SerializedName("_id")
    public var _id: String? = null

    constructor(username: String, password: String, ville: String, email: String, status_user: String, phone: String, createdAt: String, lastUpdatedAt: String, _id: String){
        this.username = username
        this.password = password
        this.ville = ville
        this.email = email
        this.status_user = status_user
        this.phone = phone
        this.createdAt = createdAt
        this.lastUpdatedAt = lastUpdatedAt
        this._id = _id
    }




}