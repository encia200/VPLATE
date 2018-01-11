package com.vplate.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.LoginPost
import com.vplate.Network.Post.Response.LoginResponse
import com.vplate.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private var networkService: NetworkService? = null // 넽웕 썰비스

    val PREFS_NAME = "LoginPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkService = ApplicationController.instance!!.networkService // 통신

        if (login_emailEdit.hasFocus()) {
//            login_emailEdit.baseli
            Toast.makeText(applicationContext, "focus", Toast.LENGTH_LONG).show()
        }

        // 비밀번호 찾기 버튼 (비밀번호 찾기 화면으로 넘어감)
        login_findPwBtn!!.setOnClickListener{
            val intent = Intent(applicationContext, PwFindActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(intent)
            finish()
        }

        // 로그인버튼
        login_loginBtn!!.setOnClickListener{
            signin()
        }
    }

    fun signin(){
        val loginResponse = networkService!!.signin(LoginPost(login_emailEdit.text.toString(), login_pwEdit.text.toString(), FirebaseInstanceId.getInstance().getToken()!!))

        loginResponse.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                if(response!!.isSuccessful){
                    // 로그인 성공했을 때
                    if (response!!.body().status.equals("success")){

                        CommonData.loginResponse = response!!.body()

                        //
                        val settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                        val editor = settings.edit()
                        editor.putString("logged", "logged")
                        editor.putString("pwd", login_pwEdit.text.toString())
                        editor.putString("user_email", login_emailEdit.text.toString())

                        editor.commit()
                        //

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                        startActivity(intent) // 화면 넘어감
                        ApplicationController.instance!!.makeToast("로그인 성공")

                        finish()
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