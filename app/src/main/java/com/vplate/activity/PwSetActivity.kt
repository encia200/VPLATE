package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.vplate.Network.ApplicationController
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.PwSetPost
import com.vplate.Network.Post.Response.SignResponse
import com.vplate.R
import kotlinx.android.synthetic.main.activity_pw_set.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PwSetActivity : AppCompatActivity() {

    var email : String? = null
    private var networkService: NetworkService? = null // 넽웕 썰비스
    var pwFlag : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pw_set)

//        CommonData.loginResponse!!.data.user_email


        networkService = ApplicationController.instance!!.networkService // 통신
        email = intent.getStringExtra("email") // 앞에서 이메일 받아오기

        pw_set_pw2Edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var pw = pw_set_pw1Edit.text.toString()
                var pwCheck = pw_set_pw2Edit.text.toString()

                if (pw_set_pw1Edit.text.length > 0 && pw_set_pw2Edit.text.length > 0) {
                    if (!pw.equals(pwCheck)) { // 다를 때
                        join_sameEmailAlert.visibility =  View.VISIBLE
                        pwFlag = 0
                    } else {
                        join_sameEmailAlert.visibility = View.GONE // 같을 때
                        pwFlag = 1
                    }
                }
            }
        }) // 요까지 비밀번호 체크

        pw_set_startBtn!!.setOnClickListener{
            if (pwFlag == 1) {
                pwSet()
            }
        }
    }

    // 비밀번호 바꾸기
    fun pwSet() {
        val pwSetResponse = networkService!!.pwSet(PwSetPost(email!!, pw_set_pw1Edit.text.toString()))

        pwSetResponse.enqueue(object : Callback<SignResponse> {

            override fun onResponse(call: Call<SignResponse>?, response: Response<SignResponse>?) {
                if (response!!.isSuccessful) {
                    ApplicationController.instance!!.makeToast("비밀번호 변경이 완료되었습니다.")

                    val intent = Intent(applicationContext, PwSetCompleteActivity::class.java)

                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<SignResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

}
