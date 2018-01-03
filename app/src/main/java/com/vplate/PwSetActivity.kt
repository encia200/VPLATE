package com.vplate

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pw_set.*

class PwSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pw_set)

        pw_set_startBtn!!.setOnClickListener{
            val intent = Intent(applicationContext, PwSetCompleteActivity::class.java)

            startActivity(intent)
        }
    }
}
