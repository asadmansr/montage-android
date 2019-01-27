package com.asadmansoor.montage.api

import com.google.gson.annotations.SerializedName

data class InfoLoginModel (
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String
)