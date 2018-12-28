package com.asadmansoor.montage.api

import com.google.gson.annotations.SerializedName

data class UserMemberModel (
    @SerializedName("title")
    var title: String,
    @SerializedName("first")
    var first: String,
    @SerializedName("last")
    var last: String
)