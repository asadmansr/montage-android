package com.asadmansoor.montage.api

import com.google.gson.annotations.SerializedName

data class InfoTimezoneModel (
    @SerializedName("offset")
    var offset: String
)