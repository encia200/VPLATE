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

                    if (response!!.body().data.template_type.equals("제품")) {
                        complete_cateImg!!.setImageResource(R.drawable.cate_icon_2)
                    }
                    else if (response!!.body().data.template_type.equals("여행")) {
                        complete_cateImg!!.setImageResource(R.drawable.cate_icon_3)
                    }
                    else if (response!!.body().data.template_type.equals("카페")) {
                        complete_cateImg!!.setImageResource(R.drawable.cate_icon_4)
                    }
                    else if (response!!.body().data.template_type.equals("푸드트럭")) {
                        complete_cateImg!!.setImageResource(R.drawable.cate_icon_5)
                    }
                    else if (response!!.body().data.template_type.equals("행사")) {
                        complete_cateImg!!.setImageResource(R.drawable.cate_icon_6)
                    }

                    complete_video_name!!.text = response!!.body().data.template_title
                    complete_postTime!!.text = response!!.body().data.mymedia_uploadtime
                    complete_video_story!!.text = response!!.body().data.template_hashtag
                    complete_video_time!!.text = "00:" + response!!.body().data.template_length.toString()
                    templatecomplete_mediaType!!.text = "사진 " + response!!.body().data.template_photoNum + "개 / 텍스트 " + response!!.body().data.template_textNum + "개 / 비디오 " + response!!.body().data.template_videoNum + "개"
                    complete_content!!.text = response!!.body().data.template_content
                }
                else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<CompleteDetailResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })
    }
}