package com.vplate.Network.Post

/**
 * Created by chosoomin on 2018. 1. 6..
 */
data class LoginData (
    var type : Int,
    var email : String,
    var name : String,
    var nickname : String,
    var push : Int,
    var profile : String
)