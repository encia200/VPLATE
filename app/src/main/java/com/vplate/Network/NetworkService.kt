package com.vplate.Network

import com.vplate.Network.Get.Response.*
import com.vplate.Network.Post.EmailCheckPost
import com.vplate.Network.Post.LoginPost
import com.vplate.Network.Post.PwAnswerCheckPost
import com.vplate.Network.Post.PwSetPost
import com.vplate.Network.Post.Response.EmailCheckResponse
import com.vplate.Network.Post.Response.LoginResponse
import com.vplate.Network.Post.Response.SignResponse
import com.vplate.Network.Put.BookmarkPost
import com.vplate.Network.Put.Response.BookmarkResponse
import okhttp3.MultipartBody
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
               @Part("type") type: RequestBody,
               @Part profile: MultipartBody.Part?): Call<SignResponse>

    // 로그인
    @POST("account/signin")
    fun signin(
            @Body loginPost : LoginPost): Call<LoginResponse>

    // 회원 가입(이메일 중복 체크)
    @POST("account/overlap")
    fun overlap(
            @Body emailPost: EmailCheckPost): Call<EmailCheckResponse>

    // 템플릿 리스트 얻어오기 (최신순)
    //13.124.195.255:3003/account/template/list/latest?type=all&cursor=0
    @GET("account/template/list/latest")
    fun getTemplates(
            @Header("tt") tt:String,
            @Query("type") type: String,
            @Query("cursor") cursor : Int
    ): Call<TemplatelistResponse>

    // 템플릿 리스트 얻어오기 (인기순)
    // 13.124.195.255:3003/account/template/list/popularity?type=all&cursor=0
    @GET("account/template/list/popularity")
    fun getTemplatesPopularity(
            @Header("tt") tt:String,
            @Query("type") type: String,
            @Query("cursor") cursor : Int
    ) : Call<TemplatelistResponse>

    // 템플릿 상세보기
    @GET("account/template/inform/detail")
    fun templateDetail(
            @Header("tt") tt:String,
            @Query("templateid") templateid: Int) : Call<TemplateDetailResponse>

    // 비밀번호 찾기
    @POST("account/setting/search")
    fun pwFind(
            @Body pwPost : PwAnswerCheckPost) : Call<SignResponse>

    //비밀번호 변경
    @POST("account/setting/change")
    fun pwSet(
            @Body loginPost : PwSetPost) : Call<SignResponse>

    // 나의 영상 리스트 (미완성)
    @GET("account/video/list/incompletion")
    fun myvideoListIncomplete(
            @Header("tt") tt:String
    ) : Call<MyVideoListResponse>

    // 나의 영상 리스트 (완성)
    @GET("account/video/list/completion")
    fun myvideoListComplete(
            @Header("tt") tt:String
    ) : Call<MyVideoListResponse>

    // 완성된 리스트 상세보기
    @GET("account/video/detail")
    fun completeDetail(
            @Header("tt") tt:String,
            @Query("mymediaid") mymediaid: Int
    ) : Call<CompleteDetailResponse>

    // 커뮤니티 리스트 (최신순)
    @GET("community/list/latest")
    fun communityLatest(
            @Header("tt") tt:String
    ) : Call<CommunityResponse>

    // 커뮤니티 리스트 (Top 10)
    @GET("community/list/ranking")
    fun communityTop10(
            @Header("tt") tt:String
    ) : Call<CommunityResponse>

     // 씬정보가져오기
    //13.124.195.255:3003/account/template/inform/scene?templateid={ templateid }
    @GET("account/template/inform/scene")
    fun ScenePhoto(
             @Header("tt") tt:String,
             @Query("templateid") templateid:Int
     ) : Call<TemplateIdResponse>

    // 찜 템플릿 영상 모아보기 (최신순)
    @GET("account/template/list/choice")
    fun getDdipTemplate(
            @Header("tt") tt:String,
            @Query("cursor") cursor : Int
    ) : Call<TemplatelistResponse>

    // 북마크 등록/취소
    @PUT("account/template/bookmark")
    fun bookmark(
            @Header("tt") tt:String,
            @Body templateid : BookmarkPost
    ) : Call<BookmarkResponse>





    //
    @GET("admin/inform")
    fun nothing(
            @Header("tt") tt:String,
            @Query("templateid") templateid : Int,
            @Query("sceneString") sceneString : String
    ) : Call<NothingResponse>
    //
}