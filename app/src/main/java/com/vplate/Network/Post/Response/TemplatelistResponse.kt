package com.vplate.Network.Post.Response

import com.vplate.Network.Post.TemplateListData

data class TemplatelistResponse(
        var status : String,
        var data : TemplateListData,
        var msg : String
)