package com.vplate.Network

import android.app.Application
import android.content.Context
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by chosoomin on 2018. 1. 4..
 */
class ApplicationController : Application() {

    var networkService: NetworkService? = null
        private set
    val baseUrl = "https://vplate.cf/"
    var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        instance = this

        buildNetwork()
    }

    fun buildNetwork() {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        networkService = retrofit.create(NetworkService::class.java)
    }

    fun makeToast(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        var instance: ApplicationController? = null
    }
}