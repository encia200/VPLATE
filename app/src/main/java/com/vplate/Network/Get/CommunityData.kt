package com.vplate.Network.Get

/**
 * Created by chosoomin on 2018. 1. 11..
 */
data class CommunityData (
        var community_id : Int,
        var email : String,
        var profile : String,
        var title : String,
        var uploadtime : String,
        var uploadvideo : String,
        var bookmark : Int,
        var hits : Int,
        var content : String
)