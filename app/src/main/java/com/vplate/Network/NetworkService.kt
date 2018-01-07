package com.vplate.Network

import com.vplate.Network.Post.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
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

    // 회원 가입(이메일 중복 체크)
    @POST("account/overlap")
    fun overlap(
            @Body emailPost: EmailCheckPost): Call<EmailCheckResponse>

    //13.124.195.255:3003/account/template/list/latest?type=all&cursor=0
    @GET("account/template/list/latest")
    fun getTemplates(
            @Header("tt") tt:String,
            @Query("type") type: String,
            @Query("cursor") cursor : Int
    ): Call<TemplatelistResponse>
    // 비밀번호 찾기
    @POST("account/setting/search")
    fun pwFind(
            @Body pwPost : PwAnswerCheckPost) : Call<SignResponse>

    @POST("account/setting/change")
    fun pwSet(
            @Body loginPost : PwSetPost) : Call<SignResponse>
}