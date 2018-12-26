package com.asadmansoor.montage

import okhttp3.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("/api/?inc=gender,name")
    fun searchUser(@Query("user") user: String): Call
}