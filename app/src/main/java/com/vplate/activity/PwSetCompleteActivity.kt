package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vplate.R
import kotlinx.android.synthetic.main.activity_pw_set_complete.*

class PwSetCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pw_set_complete)

        pw_set_complete_loginBtn!!.setOnClickListener{
            val intent = Intent(applicationContext, LoginActivity::class.java)

            startActivity(intent)
        }
    }
}
