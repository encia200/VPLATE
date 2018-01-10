package com.vplate.Network.Get.Response

import com.vplate.Network.Get.TemplateListData

data class TemplatelistResponse(
        var status : String,
        var data : TemplateListData,
        var msg : String
)