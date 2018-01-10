package com.vplate.Fragment

import android.app.Dialog
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
import com.vplate.PickVideoAdapter
import com.vplate.R
import com.vplate.Network.Get.TemplateData

/**
 * Created by chosoomin on 2018. 1. 1..
 */
class PickFragment : Fragment(), View.OnClickListener {

    private var pickList: RecyclerView? = null
    private var pickDatas: ArrayList<TemplateData>? = null
    private var adapter: PickVideoAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_pick, container, false)

        pickList = view.findViewById(R.id.pickTab_recyclerView) as RecyclerView
        pickList!!.layoutManager = LinearLayoutManager(context)

        pickDatas = ArrayList<TemplateData>()

        adapter = PickVideoAdapter(pickDatas)
        adapter!!.setOnItemClickListener(this)

        pickList!!.adapter = adapter

        return view
    }

    override fun onClick(v: View?) {
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