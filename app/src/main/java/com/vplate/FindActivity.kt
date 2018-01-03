package com.vplate

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_find.*

class FindActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)

        find_completeBtn!!.setOnClickListener{
            val intent = Intent(applicationContext, PwSetActivity::class.java)

            startActivity(intent)
        }
    }
}
