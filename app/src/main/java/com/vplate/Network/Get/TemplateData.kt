package com.vplate.Network.Get

data class TemplateData(
        var template_id: Int,
        var template_title: String,
        var template_hashtag: String,
        var template_type : String,
        var template_hits: Int,
        var template_length: Int,
        var template_uploadtime: String,
        var template_thumbnail: String
)