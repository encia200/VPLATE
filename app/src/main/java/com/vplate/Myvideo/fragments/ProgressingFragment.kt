package com.vplate.Myvideo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vplate.MyMediaCompleteAdapter
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.Get.MyMediaData
import com.vplate.Network.Get.Response.MyVideoListResponse
import com.vplate.Network.NetworkService
import com.vplate.R
import kotlinx.android.synthetic.main.fragment_myvideo_progressing.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by chosoomin on 2018. 1. 1..
 */
class ProgressingFragment : Fragment(), View.OnClickListener { // 미완성된 영상 버튼 눌렀을 경우 나타나는 프래그먼트

    private var networkService: NetworkService? = null // 넽웕 썰비스

    //
    private var progressingmediaList: RecyclerView? = null
    private var progressingmediaDatas: ArrayList<MyMediaData>? = null
    private var progressingadapter: MyMediaCompleteAdapter? = null
    //

    private var v : View? = null

    override fun onClick(v: View?) {
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater!!.inflate(R.layout.fragment_myvideo_progressing, container, false)

        v!!.myvideo_progressing_recyclerview!!.layoutManager = LinearLayoutManager(context) // 레이아웃 매니저 설정

        networkService = ApplicationController.instance!!.networkService // 통신

        progressingList()

        return v
    }

    // 미완성된 영상 리스트 가져오기
    fun progressingList() {
        val progressingResponse = networkService!!.myvideoListIncomplete(CommonData.loginResponse!!.token)

        progressingResponse.enqueue(object: Callback<MyVideoListResponse> {
            override fun onFailure(call: Call<MyVideoListResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }

            override fun onResponse(call: Call<MyVideoListResponse>?, response: Response<MyVideoListResponse>?) {
                if (response!!.isSuccessful) {
                    CommonData.videoAllList = response!!.body().data.mymedia
                    progressingmediaDatas = CommonData.videoAllList
                    progressingadapter = MyMediaCompleteAdapter(progressingmediaDatas, 0)
                    progressingadapter!!.setOnItemClickListener(this@ProgressingFragment)
                    progressingmediaList = v!!.findViewById(R.id.myvideo_progressing_recyclerview) as RecyclerView
                    progressingmediaList!!.adapter = progressingadapter
                }
                else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }
        })
    }
}