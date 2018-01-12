package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vplate.Network.Get.HorizontalViewHolder

/**
 * Created by SM-PC on 2018-01-12.
 */
class HorizontalAdapter(var dataList: ArrayList<String>?) : RecyclerView.Adapter<HorizontalViewHolder>()  {

    private var onItemClick: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val mainView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.horizon_recycler_items, parent, false)
        mainView.setOnClickListener(onItemClick)

        return HorizontalViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
//        val data = HorizontalDatas!![position]
//        holder.sceneNum.text = data.scene
//        holder.image.setImageResource(data.img)
        Glide.with(holder!!.itemView)
                .load(dataList!!.get(position))
                .into(holder!!.image)

//        holder.sceneNum.text = dataList!!.get(position).scene
    }

    override fun getItemCount(): Int {
        var count = 0

        for (i in dataList!!) {
            if (i != null) {
                count++
            }
        }

        return count
    }


    fun setOnItemClick(i: View.OnClickListener) {
        onItemClick = i
    }
}