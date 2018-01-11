package com.vplate.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.LoginPost
import com.vplate.Network.Post.Response.LoginResponse
import com.vplate.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    private var networkService: NetworkService? = null // 넽웕 썰비스
    val PREFS_NAME = "LoginPrefs"

    var loaded_email : String? = null
    var loaded_pwd : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        networkService = ApplicationController.instance!!.networkService // 통신

        // 자동로그인
        var settings: SharedPreferences? = null
        settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        if (settings.getString("logged", "")!!.toString().equals("logged")) {

            loaded_email = settings.getString("user_email", "")
            loaded_pwd = settings.getString("pwd", "")

            signin(loaded_email!!,loaded_pwd!!)
        }
        //처음에
        else {
            Handler().postDelayed({
                startActivity(Intent(applicationContext, LoginMainActivity::class.java))
                finish()
            }, 1500)
        }
    }

    // 로그인
   fun signin(email:String,pwd:String){
        val loginResponse = networkService!!.signin(LoginPost(email, pwd, FirebaseInstanceId.getInstance().getToken()!!))

        loginResponse.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                if(response!!.isSuccessful){
                    // 로그인 성공했을 때
                    if (response!!.body().status.equals("success")){
                        val settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                        val editor = settings.edit()
                        editor.putString("logged", "logged")
                        editor.putString("pwd", loaded_pwd)
                        editor.putString("user_email", loaded_email)

                        editor.commit()

                        CommonData.loginResponse = response!!.body()

                        startActivity(Intent(applicationContext, MainActivity::class.java)) // 화면 넘어감
                        finish()
                        ApplicationController.instance!!.makeToast("로그인 성공")
                    }
                }

                else {
                    ApplicationController.instance!!.makeToast("이메일이 존재하지 않거나 비밀번호가 일치하지 않습니다.")
                }
            }
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }
}