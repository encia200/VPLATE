package com.vplate.Network.Get

/**
 * Created by chosoomin on 2018. 1. 12..
 */
data class TemplateDetailData (
        var template_id : Int,
        var template_sceneNum : Int,
        var template_clip : Int,
        var template_textNum: Int,
        var template_photoNum: Int,
        var template_videoNum: Int,
        var template_title : String,
        var template_hashtag : String,
        var template_content : String,
        var template_type : String,
        var template_hits : Int,
        var template_length : Int,
        var template_bookmarkTemplate : Int,
        var template_uploadtime : String,
        var template_video : String
)