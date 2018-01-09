package com.vplate.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vplate.Network.NetworkService
import com.vplate.R

/**
 * Created by SM-PC on 2018-01-09.
 */
class NickChangeAcitivity : AppCompatActivity() {
    private var networkService: NetworkService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nicknamechange)

    }


}