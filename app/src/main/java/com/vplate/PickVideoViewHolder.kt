package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by chosoomin on 2018. 1. 4..
 */
class PickVideoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var PickVideoImage : ImageView = itemView!!.findViewById(R.id.pick_image) as ImageView
    var PickVideoName : TextView = itemView!!.findViewById(R.id.pick_title) as TextView
    var PickVideoStory : TextView = itemView!!.findViewById(R.id.pick_story) as TextView
    var PickVideoTime : TextView = itemView!!.findViewById(R.id.pick_time) as TextView

}