package com.vplate.Network.Get

/**
 * Created by chosoomin on 2018. 1. 11..
 */
data class CommunityData (
        var community_id : Int,
        var email : String,
        var nickname : String,
        var profile : String,
        var uploadtime : String,
        var content : String,
        var uploadvideo : String,
        var bookmark : Int,
        var hits : Int
)