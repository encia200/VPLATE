package com.vplate.Network

import com.vplate.Network.Post.LoginPost
import com.vplate.Network.Post.LoginResponse
import com.vplate.Network.Post.SignResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Created by chosoomin on 2018. 1. 4..
 */
interface NetworkService {

    //회원 가입(이메일 중복 체크)
    @Multipart
    @POST("account/signup")
    fun emailNicknameCheck(@Part("email") email: RequestBody,
                           @Part("pwd") pwd: RequestBody,
                           @Part("name") name: RequestBody,
                           @Part("nickname") nickname: RequestBody) : Call<SignResponse>

    // 회원 가입
    @Multipart
    @POST("account/signup")
    fun signup(@Part("email") email: RequestBody,
               @Part("pwd") pwd: RequestBody,
               @Part("answer1") answer1: RequestBody,
               @Part("answer2") answer2: RequestBody,
               @Part("nickname") nickname: RequestBody,
               @Part("name") name: RequestBody,
               @Part("fcm_key") fcm_key: RequestBody): Call<SignResponse>

    // 로그인
    @POST("account/signin")
    fun signin(
            @Body loginPost : LoginPost): Call<LoginResponse>

}