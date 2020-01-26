package com.example.barshatask.remote

import com.example.barshatask.model.Login
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("login")
    fun userLogin(@Body loginBody:Login) : Call<ResponseBody>
}