package com.vplate.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.Get.Response.CommunityResponse
import com.vplate.Network.Get.Response.TemplatelistResponse
import com.vplate.Network.Get.TemplateData
import com.vplate.Network.NetworkService
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

    private var isNew = 1 // 최신순이면 1, 인기순이면 0
    private var isHot = 0 // 인기순이면 1, 최신순이면 0

    private var cate1 = 1
    private var cate2 = 0
    private var cate3 = 0
    private var cate4 = 0
    private var cate5 = 0
    private var cate6 = 0

    private var categoryOpen = 1 // 1이면 카테고리 보여주고 0이면 안보여줌

    private var v : View?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater!!.inflate(R.layout.fragment_home, container, false)

        v!!.home_recyclerView!!.layoutManager = LinearLayoutManager(context)
//        resizeImage(v!!)

        networkService = ApplicationController.instance!!.networkService // 통신

        Log.v("::token", CommonData.loginResponse!!.token)
//        communityListLatest()

        newList("all")

        // 최신순 버튼
        v!!.home_newBtn.setOnClickListener{
            if (isNew == 0) {
                isNew = 1
                isHot = 0

                home_newBtn.setImageResource(R.drawable.line_new)
                home_hotBtn.setImageResource(R.drawable.lanking)

                if (cate1 == 1) {
                    newList("all")
                }
                else if (cate2 == 1) {
                    newList("제품")
                }
                else if (cate3 == 1) {
                    newList("여행")
                }
                else if (cate4 == 1) {
                    newList("카페")
                }
                else if (cate5 == 1) {
                    newList("푸드트럭")
                }
                else if (cate6 == 1) {
                    newList("행사")
                }
            }
        }

        // 인기순 버튼
        v!!.home_hotBtn.setOnClickListener{
            if (isHot == 0) {
                isHot = 1
                isNew = 0

                home_hotBtn.setImageResource(R.drawable.line_lanking)
                home_newBtn.setImageResource(R.drawable.icon_new)

                if (cate1 == 1) {
                    hotList("all")
                }
                else if (cate2 == 1) {
                    hotList("제품")
                }
                else if (cate3 == 1) {
                    hotList("여행")
                }
                else if (cate4 == 1) {
                    hotList("카페")
                }
                else if (cate5 == 1) {
                    hotList("푸드트럭")
                }
                else if (cate6 == 1) {
                    hotList("행사")
                }
            }

        }

        // 카테고리 버튼
        v!!.categoryBtn.setOnClickListener{
            if (categoryOpen == 1) {
                categoryOpen = 0

                v!!.home_category.visibility = View.GONE
            }
            else if (categoryOpen == 0) {
                categoryOpen = 1

                v!!.home_category.visibility = View.VISIBLE
            }
        }

        // ========= 카테고리 버튼 중에서 =========
        // 전체 버튼
        v!!.home_cate_img_1.setOnClickListener{
                cate1 = 1
                cate2 = 0
                cate3 = 0
                cate4 = 0
                cate5 = 0
                cate6 = 0

                v!!.cate1Triangle.visibility = View.VISIBLE
                v!!.cate2Triangle.visibility = View.GONE
                v!!.cate3Triangle.visibility = View.GONE
                v!!.cate4Triangle.visibility = View.GONE
                v!!.cate5Triangle.visibility = View.GONE
                v!!.cate6Triangle.visibility = View.GONE

                if (isNew == 1) {
                    newList("all")
                }
                else if (isHot == 1) {
                    hotList("all")
                }
        }

        // 제품 버튼
        v!!.home_cate_img_2.setOnClickListener{
                cate1 = 0
                cate2 = 1
                cate3 = 0
                cate4 = 0
                cate5 = 0
                cate6 = 0

                v!!.cate1Triangle.visibility = View.GONE
                v!!.cate2Triangle.visibility = View.VISIBLE
                v!!.cate3Triangle.visibility = View.GONE
                v!!.cate4Triangle.visibility = View.GONE
                v!!.cate5Triangle.visibility = View.GONE
                v!!.cate6Triangle.visibility = View.GONE

                if (isNew == 1) {
                    newList("제품")
                }
                else if (isHot == 1) {
                    hotList("제품")
                }

        }

        // 여행 버튼
        v!!.home_cate_img_3.setOnClickListener{
                cate1 = 0
                cate2 = 0
                cate3 = 1
                cate4 = 0
                cate5 = 0
                cate6 = 0

                v!!.cate1Triangle.visibility = View.GONE
                v!!.cate2Triangle.visibility = View.GONE
                v!!.cate3Triangle.visibility = View.VISIBLE
                v!!.cate4Triangle.visibility = View.GONE
                v!!.cate5Triangle.visibility = View.GONE
                v!!.cate6Triangle.visibility = View.GONE

                if (isNew == 1) {
                    newList("여행")
                }
                else if (isHot == 1) {
                    hotList("여행")
                }


        }

        // 카페 버튼
        v!!.home_cate_img_4.setOnClickListener{
                cate1 = 0
                cate2 = 0
                cate3 = 0
                cate4 = 1
                cate5 = 0
                cate6 = 0

                v!!.cate1Triangle.visibility = View.GONE
                v!!.cate2Triangle.visibility = View.GONE
                v!!.cate3Triangle.visibility = View.GONE
                v!!.cate4Triangle.visibility = View.VISIBLE
                v!!.cate5Triangle.visibility = View.GONE
                v!!.cate6Triangle.visibility = View.GONE

                if (isNew == 1) {
                    newList("카페")
                }
                else if (isHot == 1) {
                    hotList("카페")
                }


        }

        // 푸드트럭 버튼
        v!!.home_cate_img_5.setOnClickListener{
                cate1 = 0
                cate2 = 0
                cate3 = 0
                cate4 = 0
                cate5 = 1
                cate6 = 0

                v!!.cate1Triangle.visibility = View.GONE
                v!!.cate2Triangle.visibility = View.GONE
                v!!.cate3Triangle.visibility = View.GONE
                v!!.cate4Triangle.visibility = View.GONE
                v!!.cate5Triangle.visibility = View.VISIBLE
                v!!.cate6Triangle.visibility = View.GONE

                if (isNew == 1) {
                    newList("푸드트럭")
                }
                else if (isHot == 1) {
                    hotList("푸드트럭")
                }


        }

        // 행사 버튼
        v!!.home_cate_img_6.setOnClickListener{
            cate1 = 0
            cate2 = 0
            cate3 = 0
            cate4 = 0
            cate5 = 0
            cate6 = 1

            v!!.cate1Triangle.visibility = View.GONE
            v!!.cate2Triangle.visibility = View.GONE
            v!!.cate3Triangle.visibility = View.GONE
            v!!.cate4Triangle.visibility = View.GONE
            v!!.cate5Triangle.visibility = View.GONE
            v!!.cate6Triangle.visibility = View.VISIBLE

            if (isNew == 1) {
                newList("행사")
            }
            else if (isHot == 1) {
                hotList("행사")
            }
        }
        // ========= 카테고리 버튼 =========

        return v
    }

    override fun onClick(v: View?) {
       val idx: Int = home_recyclerView.getChildAdapterPosition(v)

        val intent = Intent(context, TemplateInfoActivity::class.java)
        intent.putExtra("template_id",templateDatas!![idx].template_id ) // 템플릿 id
        startActivity(intent)
    }

    // 카테고리 사진 사이즈 맞추기
    fun resizeImage(v : View){
        var param1 = v.cate1.layoutParams
        param1.height = param1.width
        v.cate1.layoutParams = param1

        var param2 = v.cate2.layoutParams
        param2.height = param2.width
        v.cate2.layoutParams = param2

        var param3 = v.cate3.layoutParams
        param3.height = param3.width
        v.cate3.layoutParams = param3

        var param4 = v.cate4.layoutParams
        param4.height = param4.width
        v.cate4.layoutParams = param4

        var param5 = v.cate5.layoutParams
        param5.height = param5.width
        v.cate5.layoutParams = param5

        var param6 = v.cate6.layoutParams
        param6.height = param6.width
        v.cate6.layoutParams = param6
    }

    // 최신순 리스트 가져오기
    fun newList(category : String?) {
        val detailResponse = networkService!!.getTemplates(CommonData.loginResponse!!.token, category!!, 0)
        detailResponse.enqueue(object : Callback<TemplatelistResponse> {
            override fun onResponse(call: Call<TemplatelistResponse>?, response: Response<TemplatelistResponse>?) {
                if (response!!.isSuccessful) {
                    CommonData.templateAllList = response!!.body().data.template
                    templateDatas = CommonData.templateAllList
                    adapter = TemplateAdapter(templateDatas)
                    adapter!!.setOnItemClickListener(this@HomeFragment)
                    templateList = v!!.findViewById(R.id.home_recyclerView) as RecyclerView
                    templateList!!.adapter = adapter
                } else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<TemplatelistResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })
    }

    // 인기순 리스트 가져오기
    fun hotList(category : String?) {
        val detailResponse = networkService!!.getTemplatesPopularity(CommonData.loginResponse!!.token, category!!, 0)
        detailResponse.enqueue(object : Callback<TemplatelistResponse> {
            override fun onResponse(call: Call<TemplatelistResponse>?, response: Response<TemplatelistResponse>?) {
                if (response!!.isSuccessful) {
                    CommonData.templateAllList = response!!.body().data.template
                    templateDatas = CommonData.templateAllList
                    adapter = TemplateAdapter(templateDatas)
                    adapter!!.setOnItemClickListener(this@HomeFragment)
                    templateList = v!!.findViewById(R.id.home_recyclerView) as RecyclerView
                    templateList!!.adapter = adapter
                } else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<TemplatelistResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })
    }

    // 커뮤니티 리스트 가져오기 (최신순)
    fun communityListLatest() {
        val communityResponse = networkService!!.communityLatest(CommonData.loginResponse!!.token)

        communityResponse.enqueue(object : Callback<CommunityResponse> {
            override fun onFailure(call: Call<CommunityResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }

            override fun onResponse(call: Call<CommunityResponse>?, response: Response<CommunityResponse>?) {
                if (response!!.isSuccessful) {
                    CommonData.communityList = response!!.body().data.community
                }
                else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }
        })
    }
}