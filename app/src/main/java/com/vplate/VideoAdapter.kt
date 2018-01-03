package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vplate.R

/**
 * Created by chosoomin on 2018. 1. 1..
 */
class VideoAdapter(var dataList : ArrayList<VideoData>?) : RecyclerView.Adapter<VideoViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onBindViewHolder(holder: VideoViewHolder?, position: Int) {
        holder!!.VideoImage.setImageResource(dataList!!.get(position).VideoImage)
        holder!!.VideoName.text = dataList!!.get(position).VideoName
        holder!!.VideoStory.text = dataList!!.get(position).VideoStory
        holder!!.VideoTime.text = dataList!!.get(position).VideoTime
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VideoViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_template, parent, false)
        mainView.setOnClickListener(onItemClick)

        return VideoViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l:View.OnClickListener){
        onItemClick = l
    }
}