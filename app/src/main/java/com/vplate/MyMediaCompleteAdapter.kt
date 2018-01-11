package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vplate.Network.Get.MyMediaData

/**
 * Created by chosoomin on 2018. 1. 11..
 */
class MyMediaCompleteAdapter(var dataList: ArrayList<MyMediaData>?, var complete: Int?) : RecyclerView.Adapter<MyMediaViewCompleteHolder>() { // complete 변수가 1로 들어오면 완성 데이터, 0이면 미완성 데이터

    private var onItemClick: View.OnClickListener? = null

    override fun onBindViewHolder(holder: MyMediaViewCompleteHolder?, position: Int) {

        if (complete == 1) {
            holder!!.template_incompleteIng.visibility = View.VISIBLE
            holder!!.template_incompleteTxt.visibility = View.GONE
        }
        else if (complete == 0) {
            holder!!.template_incompleteIng.visibility = View.GONE
            holder!!.template_incompleteTxt.visibility = View.VISIBLE
        }

        Glide.with(holder!!.itemView)
                .load(dataList!!.get(position).thumbnail)
                .into(holder!!.templateImage)

        holder.templateTitle.text = dataList!!.get(position).title
        holder.templateContent.text = dataList!!.get(position).hashtag
        holder.templateTime.text = "00:" + dataList!!.get(position).length.toString()
        if (dataList!!.get(position).type.equals("제품"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_2)
        else if (dataList!!.get(position).type.equals("여행"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_3)
        else if (dataList!!.get(position).type.equals("카페"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_4)
        else if (dataList!!.get(position).type.equals("푸드트럭"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_5)
        else if (dataList!!.get(position).type.equals("행사"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_6)
        else
            holder.templateCategory.setImageResource(R.drawable.cate_icon_6)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyMediaViewCompleteHolder {
        val mainView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_template, parent, false)
        mainView.setOnClickListener(onItemClick)

        return MyMediaViewCompleteHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener) {
//        onItemClick = l
    }
}