package com.vplate.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.vplate.ApplicationController
import com.vplate.Network.Post.SignResponse
import com.vplate.NetworkService
import com.vplate.R
import kotlinx.android.synthetic.main.activity_question.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class QuestionActivity : AppCompatActivity() {

    private val TAG : String = "LOG::Sign" // 이거슨 가입할 때 쓴당

    private var networkService: NetworkService? = null // 넽웕 썰비스

    // 앞 화면에서 받아온 애들 담아놓는 변수(가입시 필요한 이메일, 비밀번호, 이름, 닉네임)
    var getEmail: String? = null
    var getPwd: String? = null
    var getName: String? = null
    var getNickname: String? = null

    private var data: Uri? = null // 기본 이미지 설정에 쓰임
    private var profile: MultipartBody.Part? = null // 기본 이미지

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        // intent 데이터 받아오기
        val intent2: Intent = getIntent()!!

        // joinActivity에서 데이터 받아오기
        getEmail = intent2.getStringExtra("email")
        getPwd = intent2.getStringExtra("pwd")
        getName = intent2.getStringExtra("name")
        getNickname = intent2.getStringExtra("nickname")

        networkService = ApplicationController.instance!!.networkService // 통신

        // 시작하기 버튼 (로그인 화면으로)
        question_startBtn!!.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)

            sign()

            startActivity(intent)
            finish()
        }

        // 사용자 프로필 이미지를 기본 이미지로 설정
        defaultImage()
    }

    // 기본 이미지 설정
    fun defaultImage() {
        data = Uri.parse("android.resource://" + applicationContext.packageName + "/drawable/lala")

        val options = BitmapFactory.Options()

        var input: InputStream? = null
        try {
            input = contentResolver.openInputStream(this.data)
            Log.v("!!!!!!!!!!!!!!!", "사진 찾아땅")
        } catch(e: FileNotFoundException) {
            e.printStackTrace()
            Log.v("!!!!!!!!!!!!!!!", "사진 못찾아버려따")
        }

        val bitmap = BitmapFactory.decodeStream(input, null, options)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
        val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
        val photo = File(this.data.toString())

        profile = MultipartBody.Part.createFormData("profileImg", photo.name, photoBody)
    }

    // 통신(회원가입)
    fun sign() {
        val email = RequestBody.create(MediaType.parse("text/pain"), getEmail)
        val pwd = RequestBody.create(MediaType.parse("text/pain"), getPwd)
        val name = RequestBody.create(MediaType.parse("text/pain"), getName)
        val nickname = RequestBody.create(MediaType.parse("text/plain"), getNickname)
        val answer1 = RequestBody.create(MediaType.parse("text/plain"), question_q1Edit.text.toString())
        val answer2 = RequestBody.create(MediaType.parse("text/plain"), question_q2Edit.text.toString())
        val fcm_key = RequestBody.create(MediaType.parse("text/plain"), "123")

        val detailResponse = networkService!!.signup(email, pwd, answer1, answer2, nickname, name, profile!!, fcm_key)

        // Response 받은거
        detailResponse.enqueue(object : Callback<SignResponse> {
            override fun onFailure(call: Call<SignResponse>?, t: Throwable?) {
                Log.v(TAG,"가입 쀄일")
            }

            override fun onResponse(call: Call<SignResponse>, response: Response<SignResponse>) {
                if (response.isSuccessful) {
                    ApplicationController.instance!!.makeToast("가입 완료해버렸다")
                    Log.v(TAG,"가입 성공~~~~~~")
                } else {
                    Log.v(TAG, response.message())
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}