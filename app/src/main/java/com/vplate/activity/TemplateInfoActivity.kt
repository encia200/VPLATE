package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vplate.R
import kotlinx.android.synthetic.main.activity_templateinfo.*

class TemplateInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_templateinfo)

        btn_making!!.setOnClickListener {
            val intent = Intent(applicationContext, TemplateEditActivity::class.java)
            startActivity(intent)
        }

    }


}