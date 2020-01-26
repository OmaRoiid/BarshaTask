package com.example.barshatask.model.repository

import com.example.barshatask.UserLoginState
import com.example.barshatask.model.Login
import com.example.barshatask.remote.ApiServices
import com.example.barshatask.remote.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {
    private lateinit var mUserLoginState: UserLoginState
    fun userLogin(userNumber:String,userPassword:String,userLoginState: UserLoginState)
    {
      this.mUserLoginState=userLoginState
        val mRetrofit= RetrofitClient()
        mRetrofit.
            getRetrofitClient.
            create(ApiServices::class.java).
                userLogin(Login(userNumber, userPassword))
           .
            enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if( response.isSuccessful && response.code()==200) {
                        userLoginState.onUserLoginSuccessfully(response.message())
                    }
                    if(response.code()!=200) {
                        userLoginState.onUserLoginSuccessfully(response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    userLoginState.onUserLoginSuccessfully(t.message!!)

                }
            })
    }
}