package com.vplate.Network.Get.Response

import com.vplate.Network.Get.TemplateDetailData

/**
 * Created by chosoomin on 2018. 1. 12..
 */
data class TemplateDetailResponse (
        var status : String,
        var data : TemplateDetailData,
        var msg : String
)