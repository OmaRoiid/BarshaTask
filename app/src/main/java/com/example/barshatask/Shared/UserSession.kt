package com.example.barshatask.Shared

import android.content.Context
import android.content.SharedPreferences.Editor
import android.content.SharedPreferences




class UserSession(context: Context) {
    var editor: Editor? = null
    var mContext: Context? = null

    init {
        this.mContext = context
    }

    //Create login session
    fun createUserRegistertionSession(userNumber: String, userPassword: String,mSharedPreferences:SharedPreferences) {
        // Storing login value as TRUE
        editor = mSharedPreferences!!.edit()
        // Storing name in preferences
        editor?.putString(userNumber+userPassword +"data",userNumber + "\n"+userPassword)
        // commit changes
        editor?.apply()
    }
    fun checkUserExists(userNumber: String, userPassword: String,mSharedPreferences:SharedPreferences): Boolean {
        // Check if the user exists
        if (mSharedPreferences!!.contains(userNumber.trim()+ userPassword.trim()+"data")) {
            return true
        }
        return false
    }

    fun getUserInfoFor(userNumber: String, userPassword: String,mSharedPreferences:SharedPreferences){
        var userDetail: String? =mSharedPreferences!!.getString(userNumber+ userPassword +"data","userNumber or password Incorrect")
        editor = mSharedPreferences!!.edit()
        editor?.putString("display",userDetail)
        editor?.apply()
    }


}