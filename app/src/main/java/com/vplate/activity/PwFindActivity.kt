package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vplate.Network.ApplicationController
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.PwAnswerCheckPost
import com.vplate.Network.Post.Response.SignResponse
import com.vplate.R
import kotlinx.android.synthetic.main.activity_pw_find.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PwFindActivity : AppCompatActivity() {

    private var networkService: NetworkService? = null // 넽웕 썰비스

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pw_find)

        networkService = ApplicationController.instance!!.networkService // 통신

        find_completeBtn!!.setOnClickListener{
            pwFind()
        }
    }

    // 비밀번호 찾기 통신
    fun pwFind(){
        val pwFindResponse = networkService!!.pwFind(PwAnswerCheckPost(find_nameEdit.text.toString(), find_emailEdit.text.toString(), find_q1Edit.text.toString(), find_q2Edit.text.toString()))

        pwFindResponse.enqueue(object : Callback<SignResponse> {

            override fun onResponse(call: Call<SignResponse>?, response: Response<SignResponse>?) {
                if (response!!.isSuccessful) {
                    val intent = Intent(applicationContext, PwSetActivity::class.java)

                    intent.putExtra("email", find_emailEdit.text.toString())

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
