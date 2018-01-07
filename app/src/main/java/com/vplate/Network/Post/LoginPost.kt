package com.vplate.Network.Post

data class LoginPost (
        var email : String,
        var pwd : String,
        var fcm_key : String
)