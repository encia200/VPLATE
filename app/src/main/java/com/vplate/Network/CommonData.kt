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
    var communityList : ArrayList<CommunityData>? = null
    var sceneImage : ArrayList<String>? = null
    var templateAllData : ArrayList<String>? = null
    var templateAllDataString : ArrayList<String> = ArrayList()

    var videoList : ArrayList<String> = ArrayList()
}