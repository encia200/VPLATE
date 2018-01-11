package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by chosoomin on 2018. 1. 4..
 */
class PickVideoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var pickVideoImage : ImageView = itemView!!.findViewById(R.id.pick_image) as ImageView
    var pickVideoType : ImageView = itemView!!.findViewById(R.id.pick_categoryIcon) as ImageView
    var pickVideoTitle : TextView = itemView!!.findViewById(R.id.pick_title) as TextView
    var pickVideoStory : TextView = itemView!!.findViewById(R.id.pick_story) as TextView
    var pickVideoTime : TextView = itemView!!.findViewById(R.id.pick_time) as TextView
    var pickPostTime : TextView = itemView!!.findViewById(R.id.pick_postTime) as TextView
}