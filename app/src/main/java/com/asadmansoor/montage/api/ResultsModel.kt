package com.asadmansoor.montage.api

import com.google.gson.annotations.SerializedName

data class ResultsModel (
    @SerializedName("results")
    var results : List<UserModel>
)