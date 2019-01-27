package com.asadmansoor.montage.api

import com.asadmansoor.montage.model.UserAPI
import com.asadmansoor.montage.model.UserInfoAPI
import com.google.gson.Gson


class APIManager {

    fun parseUserName(json: String) : UserAPI {
        val gson = Gson()
        val params = gson.fromJson(json, UserResultsModel::class.java)

        val paramName = (params.results[0].name.first) + " " + (params.results[0].name.last)
        val paramEmail = (params.results[0].email)

        return UserAPI(name = paramName, email = paramEmail)
    }

    fun parseUserInformation(json: String) : UserInfoAPI {
        val gson = Gson()
        val params = gson.fromJson(json, InfoResultModel::class.java)

        val paramUsername = (params.results[0].login.username)
        val paramPassword = (params.results[0].login.password)
        val paramPhone = (params.results[0].phone)
        val paramCity = (params.results[0].location.city)
        val paramState = (params.results[0].location.state)
        val paramTimezone = (params.results[0].location.timezone.offset)

        return UserInfoAPI(username = paramUsername, password = paramPassword, phone = paramPhone, city = paramCity,
            state = paramState, timezone = paramTimezone)
    }
}