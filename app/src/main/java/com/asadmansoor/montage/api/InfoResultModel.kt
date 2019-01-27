package com.asadmansoor.montage.api

import com.google.gson.annotations.SerializedName

data class InfoResultModel (
    @SerializedName("results")
    var results : List<InfoModel>
)