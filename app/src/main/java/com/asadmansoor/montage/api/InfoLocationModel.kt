package com.asadmansoor.montage.api

import com.google.gson.annotations.SerializedName

data class InfoLocationModel (
    @SerializedName("city")
    var city: String,
    @SerializedName("state")
    var state: String,
    @SerializedName("timezone")
    var timezone: InfoTimezoneModel
)