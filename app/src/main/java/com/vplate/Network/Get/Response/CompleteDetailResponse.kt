package com.vplate.Network.Get.Response

import com.vplate.Network.Get.CompleteDetailData

/**
 * Created by chosoomin on 2018. 1. 12..
 */
data class CompleteDetailResponse (
        var status : String,
        var data : CompleteDetailData,
        var msg : String
)