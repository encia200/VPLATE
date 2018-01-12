package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vplate.Network.Get.TemplateData

class TemplateAdapter(var dataList: ArrayList<TemplateData>?) : RecyclerView.Adapter<TemplateViewHolder>() {

    private var onItemClick: View.OnClickListener? = null

    override fun onBindViewHolder(holder: TemplateViewHolder?, position: Int) {

        Glide.with(holder!!.itemView)
                .load(dataList!!.get(position).template_thumbnail)
                .into(holder!!.templateImage)

        holder.templateTitle.text = dataList!!.get(position).template_title
        holder.templateContent.text = dataList!!.get(position).template_hashtag
        holder.templateTime.text = "00:" + dataList!!.get(position).template_length.toString()
        holder.templateUploadTime.text = dataList!!.get(position).template_uploadtime
        if (dataList!!.get(position).template_type.equals("제품"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_2)
        else if (dataList!!.get(position).template_type.equals("여행"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_3)
        else if (dataList!!.get(position).template_type.equals("카페"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_4)
        else if (dataList!!.get(position).template_type.equals("푸드트럭"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_5)
        else if (dataList!!.get(position).template_type.equals("행사"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_6)
        else
            holder.templateCategory.setImageResource(R.drawable.cate_icon_6)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TemplateViewHolder {
        val mainView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_template, parent, false)
        mainView.setOnClickListener(onItemClick)

        return TemplateViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener) {
        onItemClick = l
    }
}