package com.vplate.Fragment

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.TemplateData
import com.vplate.Network.Post.TemplatelistResponse
import com.vplate.R
import com.vplate.TemplateAdapter
import com.vplate.activity.MakingActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), View.OnClickListener {
    private var networkService: NetworkService? = null // 넽웕 썰비스

    private var templateList: RecyclerView? = null
    private var templateDatas: ArrayList<TemplateData>? = null
    private var adapter: TemplateAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_home, container, false)

        networkService = ApplicationController.instance!!.networkService // 통신

        v!!.home_recyclerView!!.layoutManager = LinearLayoutManager(context)

        val detailResponse = networkService!!.getTemplates(CommonData.loginResponse!!.token, "all", 0)
        detailResponse.enqueue(object : Callback<TemplatelistResponse> {
            override fun onResponse(call: Call<TemplatelistResponse>?, response: Response<TemplatelistResponse>?) {
                if (response!!.isSuccessful) {
                    CommonData.templateAllList = response!!.body().data.template
                    templateDatas = CommonData.templateAllList
                    adapter = TemplateAdapter(templateDatas)
                    adapter!!.setOnItemClickListener(this@HomeFragment)
                    templateList = v.findViewById(R.id.home_recyclerView) as RecyclerView
                    templateList!!.adapter = adapter
                } else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<TemplatelistResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })

        return v
    }

    override fun onClick(v: View?) {
        val idx: Int = home_recyclerView.getChildAdapterPosition(v)
        val intent = Intent(context, MakingActivity::class.java)
        startActivity(intent)
        // 비디오 자동재생하는 다이얼로그
        val dialog = Dialog(activity)
        dialog.setContentView(R.layout.dialog_video)
        dialog.setTitle("Title...")

        // set the custom dialog components - text, image and button

        val vid = dialog.findViewById(R.id.dialog_videoView) as VideoView
        val vidUri = Uri.parse("https://youtu.be/u3TAnY6ktyU")

        vid.setVideoURI(vidUri)

        // MediaController 설정
        var mediaController = MediaController(context)
        vid.setMediaController(mediaController)

        vid.start()

        // dialogButton
        val dialogButton = dialog.findViewById(R.id.dialog_makeBtn) as Button

        dialogButton.setOnClickListener() {
            dialog.dismiss()
        }
        dialog.show()
    }

}