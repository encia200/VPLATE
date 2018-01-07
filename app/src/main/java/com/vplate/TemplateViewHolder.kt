package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class TemplateViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var templateImage: ImageView = itemView!!.findViewById(R.id.template_image) as ImageView
    var templateTitle: TextView = itemView!!.findViewById(R.id.template_title) as TextView
    var templateContent: TextView = itemView!!.findViewById(R.id.template_content) as TextView
    var templateCategory: ImageView = itemView!!.findViewById(R.id.template_cate_icon) as ImageView
    var templateTime: TextView = itemView!!.findViewById(R.id.template_time) as TextView

}