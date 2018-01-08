package com.vplate.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.Response.TemplatelistResponse
import com.vplate.Network.Post.TemplateData
import com.vplate.R
import com.vplate.TemplateAdapter
import com.vplate.activity.TemplateInfoActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), View.OnClickListener {
    private var networkService: NetworkService? = null // 넽웕 썰비스

    private var templateList: RecyclerView? = null
    private var templateDatas: ArrayList<TemplateData>? = null
    private var adapter: TemplateAdapter? = null

    //
    val PREFS_NAME = "LoginPrefs"
    //

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_home, container, false)

        networkService = ApplicationController.instance!!.networkService // 통신

        v!!.home_recyclerView!!.layoutManager = LinearLayoutManager(context)

        val detailResponse = networkService!!.getTemplates(CommonData.loginResponse!!.token, "all", 0)
        detailResponse.enqueue(object : Callback<TemplatelistResponse> {
            override fun onResponse(call: Call<TemplatelistResponse>?, response: Response<TemplatelistResponse>?) {
                if (response!!.isSuccessful) {
                    CommonData.templateAllList = response!!.body().data.template
                    templateDatas = CommonData.templateAllList
                    adapter = TemplateAdapter(templateDatas)
                    adapter!!.setOnItemClickListener(this@HomeFragment)
                    templateList = v.findViewById(R.id.home_recyclerView) as RecyclerView
                    templateList!!.adapter = adapter
                } else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<TemplatelistResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })

        return v
    }

    override fun onClick(v: View?) {
        val idx: Int = home_recyclerView.getChildAdapterPosition(v)


        val intent = Intent(context, TemplateInfoActivity::class.java)
        intent.putExtra("template_id",templateDatas!![idx].template_id ) // 템플릿 id
        startActivity(intent)
    }

}