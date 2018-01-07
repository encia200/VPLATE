package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vplate.R
import kotlinx.android.synthetic.main.activity_splash.*

class LoginMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginmain)

        // 회원가입 버튼
        splash_joinBtn!!.setOnClickListener{
            val intent = Intent(applicationContext, JoinActivity::class.java)

            startActivity(intent)
        }

        // 로그인버튼
        splash_loginBtn!!.setOnClickListener{
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}