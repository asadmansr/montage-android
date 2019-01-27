package com.asadmansoor.montage.api

import com.google.gson.annotations.SerializedName

data class InfoModel (
    @SerializedName("phone")
    var phone: String,
    @SerializedName("login")
    var login: InfoLoginModel,
    @SerializedName("location")
    var location: InfoLocationModel
)