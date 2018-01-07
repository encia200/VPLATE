package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.vplate.R

class LoginMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginmain)

        // 회원가입 버튼
        var joinBtn: Button = findViewById(R.id.splash_joinBtn) as Button
        joinBtn!!.setOnClickListener {
            val intent = Intent(applicationContext, JoinActivity::class.java)
            startActivity(intent)
        }

        // 로그인버튼
        var loginBtn: Button = findViewById(R.id.splash_loginBtn) as Button
        loginBtn!!.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}