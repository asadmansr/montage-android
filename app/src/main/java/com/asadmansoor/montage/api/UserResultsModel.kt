package com.asadmansoor.montage.api

import com.google.gson.annotations.SerializedName

data class UserResultsModel (
    @SerializedName("results")
    var results : List<UserModel>
)