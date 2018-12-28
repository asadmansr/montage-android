package com.asadmansoor.montage.api

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("gender")
    var gender: String,
    @SerializedName("name")
    var name: UserMemberModel
)