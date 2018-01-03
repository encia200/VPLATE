package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.vplate.R

/**
 * Created by SM-PC on 2018-01-01.
 */
class VideoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var VideoImage : ImageView = itemView!!.findViewById(R.id.item_image) as ImageView
    var VideoName : TextView = itemView!!.findViewById(R.id.itemTitle) as TextView
    var VideoStory : TextView = itemView!!.findViewById(R.id.itemStory) as TextView
    var VideoTime : TextView = itemView!!.findViewById(R.id.itemTime) as TextView

}