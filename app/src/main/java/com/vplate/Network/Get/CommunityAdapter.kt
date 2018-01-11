package com.vplate.Network.Get

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vplate.R

/**
 * Created by chosoomin on 2018. 1. 12..
 */
class CommunityAdapter(var dataList: ArrayList<CommunityData>?) : RecyclerView.Adapter<CommunityViewHolder>() {

    private var onItemClick: View.OnClickListener? = null

    override fun onBindViewHolder(holder: CommunityViewHolder?, position: Int) {

        Glide.with(holder!!.itemView)
                .load(dataList!!.get(position).profile)
                .into(holder!!.profile_image)

        holder!!.community_nick.text = dataList!!.get(position).nickname
        holder.community_content.text = dataList!!.get(position).content
        holder.community_time.text = dataList!!.get(position).uploadtime
        holder.community_like_text.text = dataList!!.get(position).hits.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommunityViewHolder {
        val mainView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_template, parent, false)
        mainView.setOnClickListener(onItemClick)

        return CommunityViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener) {
        onItemClick = l
    }
}