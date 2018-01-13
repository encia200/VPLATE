package com.vplate.Network.Get

/**
 * Created by chosoomin on 2018. 1. 12..
 */
data class CompleteDetailData (
        var mymedia_id : Int,
        var mymedia_completionVideo : String,
        var template_type : String,
        var mymedia_uploadtime : String,
        var template_textNum: Int,
        var template_photoNum: Int,
        var template_videoNum: Int,
        var template_title : String,
        var template_hashtag : String,
        var template_length : Int,
        var template_content : String
)