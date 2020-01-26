package com.example.barshatask.LoginScreen

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.barshatask.R
import com.example.barshatask.Shared.UserSession
import com.example.barshatask.UserLoginState
import com.example.barshatask.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity(),UserLoginState {
    //boolean var help when highlight the button at user clicked  action
    private var isClicked = true
    lateinit var mLoginViewModel: LoginViewModel
    private var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("MyFile",Context.MODE_PRIVATE)
        inputsValidation()
        btn_login.setOnClickListener {
            val userNumberEditText=et_user_number
            val userPasswordEditText=et_user_password
            val userSession= UserSession(this)
            var userNumber =userNumberEditText.text.toString().trim()
            var password =userPasswordEditText.text.toString().trim()
            if (userSession.checkUserExists(userNumber,password,sharedPreferences!!)){
                //user log before
                //Navigate to A Main Screen
            }
            else{
                mLoginViewModel=ViewModelProvider(this@MainActivity).get(LoginViewModel::class.java)
                mLoginViewModel.userLoginToserver(userNumber,password,this)

            }
        }
    }
    fun inputsValidation(){
        et_user_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(et_user_number.text.toString().length !=8 )
                {
                    et_user_number.error = "Invalid Number"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        et_user_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(et_user_password.text.toString().isEmpty())
                {
                    et_user_password.error="please enter password"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

   // highlight the button when user clicked
    fun onUserSelectType(view: View) {

             when (view.id) {
                 R.id.btn_customer -> {
                     isClicked=!isClicked
                     val resId = if (isClicked) R.color.dark_main_color else R.color.MainColor
                     btn_customer.setBackgroundResource(resId)
                 }
                 R.id.btn_shop -> {
                     isClicked=!isClicked
                     val resId = if (isClicked) R.color.dark_main_color else R.color.MainColor
                     btn_shop.setBackgroundResource(resId)
                 }
                 R.id.btn_qa3a -> {
                     isClicked=!isClicked
                     val resId = if (isClicked) R.color.dark_main_color else R.color.MainColor
                     btn_qa3a.setBackgroundResource(resId)
                 }
             }
         }

    //this three functions responsible to get  all user cases at login
    override fun onUserLoginSuccessfully(msg: String) {
        Toast.makeText(this,"مرحبا بكم ",Toast.LENGTH_SHORT).show()
    }

    override fun onUserFailedToLogin(msg: String) {
     Toast.makeText(this,"رقم الهاتف أو كلمة السر غير صحيح",Toast.LENGTH_SHORT).show()
    }

    override fun onNetworkError(msg: String) {
        Toast.makeText(this,"من فضل قم بفتح الانترنت الخاص بك",Toast.LENGTH_SHORT).show()
    }

}
