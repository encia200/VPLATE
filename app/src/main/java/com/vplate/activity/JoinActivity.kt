package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.vplate.R
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {

    var sameEmailFlag : Int = 0 // 중복 이메일 체크 플래그 (중복 이메일이 없으면 1 있으면 0)
    var pwFlag : Int = 0 // 비밀번호랑 비밀번호 확인 체크하는 플래그 (같을 때 1 다를 때 0)
    var sameNickFlag : Int = 0 // 중복 닉네임 체크 플래그 (중복 닉네임이 없으면 1 있으면 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        // 중복 이메일 체크


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
                    }
                    else {
                        join_pwDifferentAlert.visibility = View.GONE // 같을 때
                        pwFlag = 1
                    }
                }
            }
        }) // 요까지 비밀번호 체크

        // 중복 닉네임 체크




        // 다음 버튼 클릭(질문화면으로)
        join_nextBtn!!.setOnClickListener{
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
