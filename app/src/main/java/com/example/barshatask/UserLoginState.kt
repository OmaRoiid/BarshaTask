package com.example.barshatask

interface UserLoginState {
    fun onUserLoginSuccessfully(msg: String)
    fun onUserFailedToLogin (msg: String)
    fun onNetworkError(msg:String)
}