package com.vplate.Network.Post

data class LoginPost (
        var email : String,
        var pwd : String,
        var client_token : String
)