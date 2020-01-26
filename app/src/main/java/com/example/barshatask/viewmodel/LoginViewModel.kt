package com.example.barshatask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.barshatask.UserLoginState
import com.example.barshatask.model.repository.LoginRepository

class LoginViewModel (application : Application) : AndroidViewModel(application) {
    var loginRepo : LoginRepository
    init {
        loginRepo= LoginRepository()
    }
   fun  userLoginToserver(userNumber:String,userPassword:String,userLoginState: UserLoginState)
   {
      loginRepo.userLogin(userNumber,userPassword,userLoginState)
   }
}



