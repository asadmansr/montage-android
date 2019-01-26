package com.asadmansoor.montage.api

import com.asadmansoor.montage.model.UserAPI
import com.google.gson.Gson


class APIManager {

    fun parseUserName(json: String) : UserAPI {
        val gson = Gson()
        val params = gson.fromJson(json, ResultsModel::class.java)

        val paramName = (params.results[0].name.first) + " " + (params.results[0].name.last)
        val paramEmail = (params.results[0].email)

        return UserAPI(name = paramName, email = paramEmail)
    }
}