package com.vplate.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.Get.Response.TemplatelistResponse
import com.vplate.Network.Get.TemplateData
import com.vplate.Network.NetworkService
import com.vplate.PickVideoAdapter
import com.vplate.R
import kotlinx.android.synthetic.main.fragment_pick.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by chosoomin on 2018. 1. 1..
 */
class PickFragment : Fragment(), View.OnClickListener {

    private var pickList: RecyclerView? = null
    private var pickDatas: ArrayList<TemplateData>? = null
    private var adapter: PickVideoAdapter? = null

    private var networkService: NetworkService? = null // 넽웕 썰비스

    var v : View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater!!.inflate(R.layout.fragment_pick, container, false)

        networkService = ApplicationController.instance!!.networkService // 통신

        v!!.pickTab_recyclerView.layoutManager = LinearLayoutManager(context)

        ddipList()

        adapter = PickVideoAdapter(pickDatas)
        if (adapter!!.isCancel == 1) {
            adapter!!.notifyDataSetChanged()
            ddipList()
        }

        return v
    }

    override fun onClick(v: View?) {

    }

    // 찜 리스트 가져오기
    fun ddipList() {
        val ddipResponse = networkService!!.getDdipTemplate(CommonData.loginResponse!!.token, 0)

        ddipResponse.enqueue(object : Callback<TemplatelistResponse> {
            override fun onFailure(call: Call<TemplatelistResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }

            override fun onResponse(call: Call<TemplatelistResponse>?, response: Response<TemplatelistResponse>?) {
                if (response!!.isSuccessful) {
                    CommonData.pickAllList = response!!.body().data.template
                    pickDatas = CommonData.pickAllList
                    adapter = PickVideoAdapter(pickDatas)
                    adapter!!.setOnItemClickListener(this@PickFragment)
                    pickList = v!!.findViewById(R.id.pickTab_recyclerView) as RecyclerView
                    pickList!!.adapter = adapter
                }
                else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }
        })
    }
}