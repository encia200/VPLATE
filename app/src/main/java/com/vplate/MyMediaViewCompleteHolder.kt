package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by chosoomin on 2018. 1. 11..
 */
class MyMediaViewCompleteHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var templateImage: ImageView = itemView!!.findViewById(R.id.template_image) as ImageView
    var templateTitle: TextView = itemView!!.findViewById(R.id.template_title) as TextView
    var templateContent: TextView = itemView!!.findViewById(R.id.template_content) as TextView
    var templateCategory: ImageView = itemView!!.findViewById(R.id.template_cate_icon) as ImageView
    var templateTime: TextView = itemView!!.findViewById(R.id.template_time) as TextView
    var templateUploadTime : TextView = itemView!!.findViewById(R.id.template_uploadTime) as TextView

    var template_incompleteIng: ImageView = itemView!!.findViewById(R.id.template_incompleteIng) as ImageView
    var template_incompleteTxt : RelativeLayout = itemView!!.findViewById(R.id.template_incompleteTxt) as RelativeLayout
}