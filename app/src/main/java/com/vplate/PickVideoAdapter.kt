package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by chosoomin on 2018. 1. 4..
 */
class PickVideoAdapter(var dataList : ArrayList<VideoData>?) : RecyclerView.Adapter<PickVideoViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onBindViewHolder(holder: PickVideoViewHolder?, position: Int) {
        holder!!.PickVideoImage.setImageResource(dataList!!.get(position).VideoImage)
        holder.PickVideoName.text = dataList!!.get(position).VideoName
        holder.PickVideoStory.text = dataList!!.get(position).VideoStory
        holder.PickVideoTime.text = dataList!!.get(position).VideoTime
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PickVideoViewHolder {
        val view : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_pick, parent, false)
        view.setOnClickListener(onItemClick)

        return PickVideoViewHolder(view)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener){
        onItemClick = l
    }

}