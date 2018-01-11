package com.vplate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by SM-PC on 2018-01-12.
 */
internal class HorizontalAdapter : RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>() {

    private var onItemClick: View.OnClickListener? = null

    private var HorizontalDatas: ArrayList<HorizontalData>? = null

    fun setData(list: ArrayList<HorizontalData>) {
        HorizontalDatas = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {

        // 사용할 아이템의 뷰를 생성해준다.
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.horizon_recycler_items, parent, false)

        val holder = HorizontalViewHolder(view)
        view.setOnClickListener(onItemClick)

        return holder
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        val data = HorizontalDatas!![position]
        holder.sceneNum.text = data.scene
        holder.image.setImageResource(data.img)

    }

    override fun getItemCount(): Int {
        return HorizontalDatas!!.size
    }

    fun setOnItemClick(i: View.OnClickListener) {
        onItemClick = i
    }


    internal class HorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.template_timeline_itemimage) as ImageView
        var sceneNum: TextView = itemView.findViewById(R.id.scene_num) as TextView

    }

    internal class HorizontalData(val scene: String, val img: Int)





}