package com.sw.vplate.Fragment

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.VideoView
import com.sw.vplate.R
import com.sw.vplate.VideoAdapter
import com.sw.vplate.VideoData



/**
 * Created by SM-PC on 2018-01-01.
 */
class HomeFragment: Fragment(), View.OnClickListener {

    private var templateList : RecyclerView ?= null
    private var templateDatas : ArrayList<VideoData>?= null
    private var adapter : VideoAdapter ?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_home, container, false)

        templateList = v.findViewById(R.id.mainRecycler) as RecyclerView
        templateList!!.layoutManager = GridLayoutManager(context, 2)

        templateDatas = ArrayList<VideoData>()
        templateDatas!!.add(VideoData(R.drawable.jungwoo, "반짝반짝 네온사인", "#해시태그 #해시태그 #해시태그", "0:30"))
        templateDatas!!.add(VideoData(R.drawable.jungwoo, "반짝반짝 네온사인", "#해시태그 #해시태그 #해시태그", "0:30"))
        templateDatas!!.add(VideoData(R.drawable.jungwoo, "반짝반짝 네온사인", "#해시태그 #해시태그 #해시태그", "0:30"))
        templateDatas!!.add(VideoData(R.drawable.jungwoo, "반짝반짝 네온사인", "#해시태그 #해시태그 #해시태그", "0:30"))
        templateDatas!!.add(VideoData(R.drawable.jungwoo, "반짝반짝 네온사인", "#해시태그 #해시태그 #해시태그", "0:30"))

        adapter = VideoAdapter(templateDatas)
        adapter!!.setOnItemClickListener(this)

        templateList!!.adapter = adapter

        return v
    }

    override fun onClick(v: View?) {
//        val idx : Int = mainRecycler!!.getChildAdapterPosition(v)

//        val name : String? = templateDatas!!.get(idx).VideoName
//        Toast.makeText(context, name, Toast.LENGTH_SHORT).show()

        // 비디오 자동재생하는 다이얼로그
        val dialog = Dialog(activity)
        dialog.setContentView(R.layout.dialog_video)
        dialog.setTitle("Title...")

        // set the custom dialog components - text, image and button

        val vid = dialog.findViewById(R.id.videoView) as VideoView
        val vidUri = Uri.parse("https://youtu.be/u3TAnY6ktyU")
        vid.setVideoURI(vidUri)
        vid.start()

        val dialogButton = dialog.findViewById(R.id.dialogButtonOK) as Button
        // if button is clicked, close the custom dialog

        dialogButton.setOnClickListener(){
            dialog.dismiss()
        }



        dialog.show()
    }



}