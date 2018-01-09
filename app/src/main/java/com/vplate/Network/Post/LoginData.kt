package com.vplate.Network.Post

/**
 * Created by chosoomin on 2018. 1. 6..
 */
data class LoginData (
    var user_type : Int,
var user_email : String,
var user_name : String,
var user_nickname : String,
var user_push : Int,
var user_profile : String
)