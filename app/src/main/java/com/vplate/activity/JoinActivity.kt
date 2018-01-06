package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.vplate.Network.ApplicationController
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.EmailCheckPost
import com.vplate.Network.Post.EmailCheckResponse
import com.vplate.R
import kotlinx.android.synthetic.main.activity_join.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivity : AppCompatActivity() {

    var sameEmailFlag: Int = 0 // 중복 이메일 체크 플래그 (중복 이메일이 없으면 1 있으면 0)
    var pwFlag: Int = 0 // 비밀번호랑 비밀번호 확인 체크하는 플래그 (같을 때 1 다를 때 0)

    private var networkService: NetworkService? = null // 넽웕 썰비스

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        networkService = ApplicationController.instance!!.networkService // 통신

        // 이메일 중복 체크(포커스 아웃될 때마다)
        join_emailEdit.setOnFocusChangeListener { View, hasFocus ->
            if (!hasFocus) {// 포커스 아웃
                if (join_emailEdit.text.length > 0) { // 하나라도 썼으면
                    emailCheck() //  중복 이메일 체크
                }
            }
        }

        // 비밀번호랑 비밀번호 확인 체크
        join_pwCheckEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var pw = join_pwEdit.text.toString()
                var pwCheck = join_pwCheckEdit.text.toString()

                if (join_pwEdit.text.length > 0 && join_pwCheckEdit.text.length > 0) {
                    if (!pw.equals(pwCheck)) { // 다를 때
                        join_pwDifferentAlert.visibility = View.VISIBLE
                        pwFlag = 0
                    } else {
                        join_pwDifferentAlert.visibility = View.GONE // 같을 때
                        pwFlag = 1
                    }
                }
            }
        }) // 요까지 비밀번호 체크

        // 다음 버튼 클릭(질문화면으로)
        join_nextBtn!!.setOnClickListener {
            // 모두 안적으면 다음 화면으로 못넘어감
            if (join_emailEdit.text.length == 0 || join_pwEdit.text.length == 0 || join_nameEdit.text.length == 0 || join_nicknameEdit.text.length == 0) {
                Toast.makeText(applicationContext, "모든 질문에 답해주세요.", Toast.LENGTH_LONG).show()
            } else { // 모두 적음
                // 이 때 중복 이메일 없고, 비밀번호 같은지 확인 후에 넘기기
                if (sameEmailFlag == 1 && pwFlag == 1) {
                    Toast.makeText(applicationContext, "중복 없음", Toast.LENGTH_LONG).show()

                    val intent = Intent(applicationContext, QuestionActivity::class.java)

                    intent.putExtra("email", join_emailEdit!!.text.toString())
                    intent.putExtra("pwd", join_pwEdit!!.text.toString())
                    intent.putExtra("name", join_nameEdit!!.text.toString())
                    intent.putExtra("nickname", join_nicknameEdit!!.text.toString())

                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    // 이메일 중복 체크 함수
    fun emailCheck() {
        val detailResponse = networkService!!.overlap(EmailCheckPost(join_emailEdit.text.toString())) // 이메일 중복 체크

        // Response 받은거
        detailResponse.enqueue(object : Callback<EmailCheckResponse> {
            override fun onFailure(call: Call<EmailCheckResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }

            override fun onResponse(call: Call<EmailCheckResponse>, response: Response<EmailCheckResponse>) {
                if (response.isSuccessful) { // 이메일 중복이 아닌 경우
                    Log.v("::Email Check", "이메일 중복 X")
                    sameEmailFlag = 1
                    join_sameEmailAlert.visibility = View.GONE
                } else { // 이메일 중복인 경우
                    if (response.code().toString() == "401") {
                        Log.v("::Email Check", "이메일 중복 O")
                        sameEmailFlag = 0
                        join_sameEmailAlert.visibility = View.VISIBLE
                    } else if (response.code().toString() == "500") {
                        Log.v("::Email Check", "internal error")
                    }
                }
            }
        })
    }
}
