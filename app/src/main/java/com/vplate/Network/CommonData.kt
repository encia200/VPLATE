package com.vplate.Network

import com.vplate.Network.Get.CommunityData
import com.vplate.Network.Get.MyMediaData
import com.vplate.Network.Get.TemplateData
import com.vplate.Network.Post.Response.LoginResponse

/**
 * Created by chosoomin on 2018. 1. 5..
 */
object CommonData {
    var loginResponse: LoginResponse? = null
    var templateAllList: ArrayList<TemplateData>? = null
    var videoAllList : ArrayList<MyMediaData>? = null
    var pickAllList : ArrayList<TemplateData>? = null
    var videoList : ArrayList<CommunityData>? = null
}