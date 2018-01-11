package com.vplate

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.Get.TemplateData
import com.vplate.Network.NetworkService
import com.vplate.Network.Put.BookmarkPost
import com.vplate.Network.Put.Response.BookmarkResponse
import kotlinx.android.synthetic.main.item_pick.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by chosoomin on 2018. 1. 4..
 */
class PickVideoAdapter(var dataList : ArrayList<TemplateData>?) : RecyclerView.Adapter<PickVideoViewHolder>() {

    private var onItemClick : View.OnClickListener? = null
    private var networkService: NetworkService? = null // 넽웕 썰비스
    private var templateid : Int? = null

    var isCancel : Int = 0

    override fun onBindViewHolder(holder: PickVideoViewHolder?, position: Int) {

        //
        templateid = dataList!!.get(position).template_id
        //

        Glide.with(holder!!.itemView)
                .load(dataList!!.get(position).template_thumbnail)
                .into(holder!!.pickVideoImage)

        holder.pickVideoTitle.text = dataList!!.get(position).template_title
        holder.pickVideoStory.text = dataList!!.get(position).template_hashtag
        holder.pickVideoTime.text = "00:" + dataList!!.get(position).template_length.toString()
        holder.pickPostTime.text = dataList!!.get(position).template_uploadtime

        if (dataList!!.get(position).template_type.equals("제품"))
            holder.pickVideoType.setImageResource(R.drawable.cate_icon_2)
        else if (dataList!!.get(position).template_type.equals("여행"))
            holder.pickVideoType.setImageResource(R.drawable.cate_icon_3)
        else if (dataList!!.get(position).template_type.equals("카페"))
            holder.pickVideoType.setImageResource(R.drawable.cate_icon_4)
        else if (dataList!!.get(position).template_type.equals("푸드트럭"))
            holder.pickVideoType.setImageResource(R.drawable.cate_icon_5)
        else if (dataList!!.get(position).template_type.equals("행사"))
            holder.pickVideoType.setImageResource(R.drawable.cate_icon_6)
        else
            holder.pickVideoType.setImageResource(R.drawable.cate_icon_6)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PickVideoViewHolder {
        val view : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_pick, parent, false)
        view.setOnClickListener(onItemClick)

        networkService = ApplicationController.instance!!.networkService // 통신

        isCancel = 0

        view!!.pick_likeBtn.setOnClickListener{
            // 하트 버튼 누르면 찜 목록에서 해당 템플릿이 사라져야 함
            bookmarkCancel()
        }


        return PickVideoViewHolder(view)
    }

    override fun getItemCount(): Int = dataList!!.size
    fun setOnItemClickListener(l: View.OnClickListener){
//        onItemClick = l
    }

//    // 찜 리스트 가져오기
//    fun ddipList() {
//        val ddipResponse = networkService!!.getDdipTemplate(CommonData.loginResponse!!.token, 0)
//
//        ddipResponse.enqueue(object : Callback<TemplatelistResponse> {
//            override fun onFailure(call: Call<TemplatelistResponse>?, t: Throwable?) {
//                ApplicationController.instance!!.makeToast("통신 오류")
//            }
//
//            override fun onResponse(call: Call<TemplatelistResponse>?, response: Response<TemplatelistResponse>?) {
//                if (response!!.isSuccessful) {
//                    CommonData.pickAllList = response!!.body().data.template
//                }
//                else {
//                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
//                }
//            }
//        })
//    }

    fun bookmarkCancel() {
        val cancelResponse = networkService!!.bookmark(CommonData.loginResponse!!.token, BookmarkPost(templateid!!))

        cancelResponse.enqueue(object : Callback<BookmarkResponse> {
            override fun onFailure(call: Call<BookmarkResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }

            override fun onResponse(call: Call<BookmarkResponse>?, response: Response<BookmarkResponse>?) {
                if (response!!.isSuccessful) {
                    Log.v("::bookmark", response!!.body().msg)
                    isCancel = 1
                }
                else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }
        })
    }
}