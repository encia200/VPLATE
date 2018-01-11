package com.vplate.Network.Get.Response

import com.vplate.Network.Get.MyMediaListData

/**
 * Created by chosoomin on 2018. 1. 11..
 */
data class MyVideoListResponse (
        var status : String,
        var data : MyMediaListData,
        var msg : String
)