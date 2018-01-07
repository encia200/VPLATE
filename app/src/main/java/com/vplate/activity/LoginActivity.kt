package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.LoginPost
import com.vplate.Network.Post.LoginResponse
import com.vplate.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private var networkService: NetworkService? = null // 넽웕 썰비스

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkService = ApplicationController.instance!!.networkService // 통신

        // 비밀번호 찾기 버튼 (비밀번호 찾기 화면으로 넘어감)
        login_findPwBtn!!.setOnClickListener{
            val intent = Intent(applicationContext, FindActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        // 로그인버튼
        login_loginBtn!!.setOnClickListener{
            signin()
        }
    }

    // 로그인
    fun signin(){
        val loginResponse = networkService!!.signin(LoginPost(login_emailEdit.text.toString(), login_pwEdit.text.toString(), FirebaseInstanceId.getInstance().getToken()!!))
        loginResponse.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        CommonData.loginResponse = response!!.body()
                        startActivity(Intent(applicationContext, MainActivity::class.java)) // 화면 넘어감
                        ApplicationController.instance!!.makeToast("로그인 성공")
                    }
                    else{
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }
            }
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }
}