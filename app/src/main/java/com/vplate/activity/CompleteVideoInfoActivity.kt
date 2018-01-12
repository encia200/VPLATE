package com.vplate.activity

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.Get.Response.CompleteDetailResponse
import com.vplate.Network.NetworkService
import com.vplate.R
import kotlinx.android.synthetic.main.activity_complete_video_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompleteVideoInfoActivity : AppCompatActivity() {

    private var networkService: NetworkService? = null // 넽웕 썰비스

    var mediaId : Int? = null

    private var PB : ProgressBack? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_video_info)

        PB = ProgressBack(applicationContext)

        networkService = ApplicationController.instance!!.networkService // 통신

        mediaId = intent.getIntExtra("mymediaid", 999)

        completeDetailData()



        // 앨범에 다운로드 하기 버튼
        complete_album_downBtn!!.setOnClickListener{
            PB!!.execute("")
        }
    }

    fun completeDetailData() {
        val detailResponse = networkService!!.completeDetail(CommonData.loginResponse!!.token, mediaId!!)

        detailResponse.enqueue(object : Callback<CompleteDetailResponse> {
            override fun onResponse(call: Call<CompleteDetailResponse>?, response: Response<CompleteDetailResponse>?) {
                if (response!!.isSuccessful) {
                    var uri = Uri.parse(response!!.body().data.mymedia_completionVideo)

                    complete_video_play!!.setVideoURI(uri)
                    complete_video_play!!.start()

                } else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<CompleteDetailResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })
    }
}