package com.vplate.Network.Post.Response

import com.vplate.Network.Post.LoginData

/**
 * Created by chosoomin on 2018. 1. 5..
 */
data class LoginResponse (
        var status : String,
        var data : LoginData,
        var token : String,
        var msg : String
)