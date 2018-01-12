package com.vplate.Myvideo.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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
import com.vplate.activity.CompleteVideoInfoActivity
import kotlinx.android.synthetic.main.fragment_myvideo_complete.*
import kotlinx.android.synthetic.main.fragment_myvideo_complete.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompleteFragment : Fragment(), View.OnClickListener { // 완성된 영상 버튼 눌렀을 경우 나타나는 프래그먼트

    private var networkService: NetworkService? = null // 넽웕 썰비스

    //
    private var mediaList: RecyclerView? = null
    private var mediaDatas: ArrayList<MyMediaData>? = null
    private var adapter: MyMediaCompleteAdapter? = null
    //

    private var v : View? = null

    override fun onClick(v: View?) {
        val idx: Int = myvideo_complete_recyclerview.getChildAdapterPosition(v)

        val intent = Intent(context, CompleteVideoInfoActivity::class.java)
        intent.putExtra("mymediaid", mediaDatas!![idx].mymedia_id)

        Log.v("dd", "ssdasd")

        startActivity(intent)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater!!.inflate(R.layout.fragment_myvideo_complete, container, false)

        v!!.myvideo_complete_recyclerview!!.layoutManager = LinearLayoutManager(context) // 레이아웃 매니저 설정

        networkService = ApplicationController.instance!!.networkService // 통신

        completeList()

        return v
    }

    // 완성 리스트 가져오기
    fun completeList() {
        val completeResponse = networkService!!.myvideoListComplete(CommonData.loginResponse!!.token)

        completeResponse.enqueue(object : Callback<MyVideoListResponse> {
            override fun onResponse(call: Call<MyVideoListResponse>?, response: Response<MyVideoListResponse>?) {
                if (response!!.isSuccessful) {
                    CommonData.videoAllList = response!!.body().data.mymedia
                    mediaDatas = CommonData.videoAllList
                    adapter = MyMediaCompleteAdapter(mediaDatas, 1)
                    adapter!!.setOnItemClickListener(this@CompleteFragment)
                    mediaList = v!!.findViewById(R.id.myvideo_complete_recyclerview) as RecyclerView
                    mediaList!!.adapter = adapter
                } else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<MyVideoListResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })
    }


}