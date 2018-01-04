package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vplate.R
import kotlinx.android.synthetic.main.activity_making.*

/**
 * Created by SM-PC on 2018-01-04.
 */
class MakingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_making)



        btn_making!!.setOnClickListener{
            val intent = Intent(applicationContext, VideoEditActivity::class.java)

            startActivity(intent)

        }

    }


}