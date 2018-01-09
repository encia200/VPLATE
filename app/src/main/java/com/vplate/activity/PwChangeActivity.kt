package com.vplate.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.PwSetPost
import com.vplate.Network.Post.Response.SignResponse
import com.vplate.R.layout.activity_pwchange
import kotlinx.android.synthetic.main.activity_pwchange.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by SM-PC on 2018-01-09.
 */
class PwChangeActivity:AppCompatActivity() {

    var email : String? = null
    var pwFlag : Int = 0

    private var networkService: NetworkService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_pwchange)
        networkService = ApplicationController.instance!!.networkService//네트워크 통신
        email = CommonData.loginResponse!!.data.user_email

        // 비밀번호 가져오기
        var settings : SharedPreferences? = null
        settings = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        var pwd = settings!!.getString("pwd", "")


        checking_btn!!.setOnClickListener(){
            Toast.makeText(applicationContext, pwd, Toast.LENGTH_LONG).show()

            if (pwd.equals(pw_current.text.toString()))
            {
                pwSet()
            }else{
                Toast.makeText(applicationContext,"비밀번호가 틀렸습니다.",Toast.LENGTH_LONG).show()
            }

        }




    }
    fun pwSet() {
        val pwSetResponse = networkService!!.pwSet(PwSetPost(email!!, changed_pw.text.toString()))

        pwSetResponse.enqueue(object : Callback<SignResponse> {

            override fun onResponse(call: Call<SignResponse>?, response: Response<SignResponse>?) {
                if (response!!.isSuccessful) {
                    ApplicationController.instance!!.makeToast("비밀번호 변경이 완료되었습니다.")

                    finish()
                }
            }

            override fun onFailure(call: Call<SignResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }



}