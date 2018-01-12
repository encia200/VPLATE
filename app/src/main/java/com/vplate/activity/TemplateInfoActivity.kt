package com.vplate.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.MediaController
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.Get.Response.TemplateDetailResponse
import com.vplate.Network.NetworkService
import com.vplate.Network.Put.BookmarkPost
import com.vplate.Network.Put.Response.BookmarkResponse
import com.vplate.R
import kotlinx.android.synthetic.main.activity_templateinfo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TemplateInfoActivity : AppCompatActivity() {

    var templateId : Int? = null
    private var networkService: NetworkService? = null // 넽웕 썰비스
    var bookmark = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_templateinfo)

        networkService = ApplicationController.instance!!.networkService // 통신

        var mediaController = MediaController(video_play!!.context)
        video_play!!.setMediaController(mediaController)

        templateId = intent.getIntExtra("template_id", 5)

        getDetailData()

        Log.v("bookmark", bookmark.toString())

        if (bookmark == 1) {
            bookmarkBtn!!.setImageResource(R.drawable.ic_favorite_white_36_px)
        }
        else if (bookmark == 0) {
            bookmarkBtn!!.setImageResource(R.drawable.shape_1)
        }



        // 북마크 (하트) 버튼 눌렀을 때
        bookmarkBtn!!.setOnClickListener{
            bookmarkAddDelete()
        }

        btn_making!!.setOnClickListener {
            val intent = Intent(applicationContext, TemplateEditActivity::class.java)

            intent.putExtra("templateId", templateId!!)

            startActivity(intent)
        }
    }

    // 상세 정보 가져오기
    fun getDetailData() {
        val detailResponse = networkService!!.templateDetail(CommonData.loginResponse!!.token, templateId!!)

        detailResponse.enqueue(object : Callback<TemplateDetailResponse> {
            override fun onResponse(call: Call<TemplateDetailResponse>?, response: Response<TemplateDetailResponse>?) {
                if (response!!.isSuccessful) {
                    var uri = Uri.parse(response!!.body().data.template_video)

                    video_play!!.setVideoURI(uri)
                    video_play!!.start()

                    bookmark = response!!.body().data.template_bookmarkTemplate

                } else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<TemplateDetailResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })
    }

    // 북마크 추가/삭제
    fun bookmarkAddDelete() {
        val bookmarkResponse = networkService!!.bookmark(CommonData.loginResponse!!.token, BookmarkPost(templateId!!))

        bookmarkResponse.enqueue(object: Callback<BookmarkResponse> {
            override fun onResponse(call: Call<BookmarkResponse>?, response: Response<BookmarkResponse>?) {
                if (response!!.isSuccessful) {
                    if (response!!.body().bookmark == 1) { // 북마크 된 상태
                        bookmarkBtn!!.setImageResource(R.drawable.ic_favorite_white_36_px)
                    }
                    else if (response!!.body().bookmark == 0) { // 북마크 안 된 상태
                        bookmarkBtn!!.setImageResource(R.drawable.shape_1)
                    }

                } else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<BookmarkResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })
    }




}