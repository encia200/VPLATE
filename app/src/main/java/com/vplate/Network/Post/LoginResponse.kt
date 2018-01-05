package com.vplate.Network.Post

/**
 * Created by chosoomin on 2018. 1. 5..
 */
data class LoginResponse (
        var status : String,
        var type : Int,
        var token : String,
        var msg : String
)