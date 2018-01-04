package com.vplate

import com.vplate.Network.Post.SignResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Created by chosoomin on 2018. 1. 4..
 */
interface NetworkService {

    //회원 가입
    @Multipart
    @POST("account/signup")
    fun signup(@Part("email") email: RequestBody,
               @Part("pwd") pwd: RequestBody,
               @Part("answer1") answer1: RequestBody,
               @Part("answer2") answer2: RequestBody,
               @Part("nickname") nickname: RequestBody,
               @Part("name") name: RequestBody,
               @Part profile: MultipartBody.Part,
               @Part("fcm_key") fcm_key: RequestBody): Call<SignResponse>

}