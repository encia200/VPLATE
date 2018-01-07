package com.vplate.Network

import com.vplate.Network.Post.EmailCheckPost
import com.vplate.Network.Post.LoginPost
import com.vplate.Network.Post.PwAnswerCheckPost
import com.vplate.Network.Post.PwSetPost
import com.vplate.Network.Post.Response.EmailCheckResponse
import com.vplate.Network.Post.Response.LoginResponse
import com.vplate.Network.Post.Response.SignResponse
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
    // 회원 가입
    @Multipart
    @POST("account/signup")
    fun signup(@Part("email") email: RequestBody,
               @Part("pwd") pwd: RequestBody,
               @Part("answer1") answer1: RequestBody,
               @Part("answer2") answer2: RequestBody,
               @Part("name") name: RequestBody,
               @Part("nickname") nickname: RequestBody): Call<SignResponse>

    // 로그인
    @POST("account/signin")
    fun signin(
            @Body loginPost : LoginPost): Call<LoginResponse>

    //회원 가입(이메일 중복 체크)
    @POST("account/overlap")
    fun overlap(
            @Body emailPost : EmailCheckPost) : Call<EmailCheckResponse>

    // 비밀번호 찾기
    @POST("account/setting/search")
    fun pwFind(
            @Body pwPost : PwAnswerCheckPost) : Call<SignResponse>

    @POST("account/setting/change")
    fun pwSet(
            @Body loginPost : PwSetPost) : Call<SignResponse>
}