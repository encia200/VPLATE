package com.vplate.Network.Get.Response

import com.vplate.Network.Get.CommunityListData

/**
 * Created by chosoomin on 2018. 1. 11..
 */
data class CommunityResponse (
        var status : String,
        var data : CommunityListData,
        var msg : String
)