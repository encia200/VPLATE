package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vplate.R
import com.vplate.activity.QuestionActivity
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        join_nextBtn!!.setOnClickListener{
            val intent = Intent(applicationContext, QuestionActivity::class.java)

            startActivity(intent)
        }
    }
}
