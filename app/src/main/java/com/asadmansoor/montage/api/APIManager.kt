package com.asadmansoor.montage.api

import com.google.gson.Gson


class APIManager {

    fun parseUserName(json: String) : String {
        val gson = Gson()
        val params = gson.fromJson(json, ResultsModel::class.java)

        return (params.results[0].name.first) + " " + (params.results[0].name.last)
    }
}