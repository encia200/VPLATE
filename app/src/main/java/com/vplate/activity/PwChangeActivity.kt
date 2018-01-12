package com.vplate.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.vplate.Network.ApplicationController
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

    var pwd : String? = null
    var email : String? = null
    var pwFlag : Int = 0


    var settings : SharedPreferences? = null

    private var networkService: NetworkService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_pwchange)
        networkService = ApplicationController.instance!!.networkService//네트워크 통신

        // 비밀번호 가져오기
        settings = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        pwd = settings!!.getString("pwd", "")
        email = settings!!.getString("user_email", "")


        checking_btn!!.setOnClickListener(){
            if (pwd.equals(pw_current.text.toString()))
            {
                pwSet()
            }else{
                Toast.makeText(applicationContext,"비밀번호가 틀렸습니다.",Toast.LENGTH_LONG).show()
            }

        }


        changed_pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var pw = pw_current.text.toString()
                var pwCheck = changed_pw.text.toString()

                if (pw_current.text.length > 0 && changed_pw.text.length > 0) {
                    if (!pw.equals(pw_current)) { // 다를 때
                        mypage_pwDifferentAlert.visibility = View.VISIBLE
                        pwFlag = 0
                    } else {
                        mypage_pwDifferentAlert.visibility = View.GONE // 같을 때
                        pwFlag = 1
                    }
                }
            }
        })


    }
    fun pwSet() {
        val pwSetResponse = networkService!!.pwSet(PwSetPost(email!!, changed_pw.text.toString()!!))

        pwSetResponse.enqueue(object : Callback<SignResponse> {

            override fun onResponse(call: Call<SignResponse>?, response: Response<SignResponse>?) {
                if (response!!.isSuccessful) {
                    ApplicationController.instance!!.makeToast("비밀번호 변경이 완료되었습니다.")


                    val editor = settings!!.edit()
                    editor.putString("pwd", changed_pw.text.toString())
                    editor.commit()
                    finish()
                }
            }

            override fun onFailure(call: Call<SignResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }



}