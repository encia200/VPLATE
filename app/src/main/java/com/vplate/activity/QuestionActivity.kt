package com.vplate.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.vplate.Network.ApplicationController
import com.vplate.Network.NetworkService
import com.vplate.Network.Post.Response.SignResponse
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
import java.io.FileOutputStream

class QuestionActivity : AppCompatActivity() {

    private val TAG: String = "LOG::Sign" // 이거슨 가입할 때 쓴당

    private var networkService: NetworkService? = null // 넽웕 썰비스

    private var prof_img : MultipartBody.Part? = null
    private var flag: Int = 1

    // 앞 화면에서 받아온 애들 담아놓는 변수(가입시 필요한 이메일, 비밀번호, 이름, 닉네임)
    var getEmail: String? = null
    var getPwd: String? = null
    var getName: String? = null
    var getNickname: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        var pictureBitmap : Bitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.icon_person)

        var f : File?= File(applicationContext.cacheDir, "icon_prof")
        f!!.createNewFile()
        var bos  = ByteArrayOutputStream()
        pictureBitmap.compress(Bitmap.CompressFormat.JPEG,20,bos)
        var bitmapdata : ByteArray = bos.toByteArray()
        var fos = FileOutputStream(f)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()
        val photoBody = RequestBody.create(MediaType.parse("image/jpg"),bos.toByteArray())
//        val photoBody = RequestBody.create(MediaType.parse("image/jpg"),bos.toByteArray())
        prof_img = MultipartBody.Part.createFormData("profile",f.name,photoBody)

//        prof_img = MultipartBody.Part.createFormData("file",pictureBitmap)
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
            // 질문에 대답을 안적으면 화면 못넘김
            if (question_q1Edit.text.length == 0 || question_q1Edit.text.length == 0) {
                Toast.makeText(applicationContext, "모든 질문에 답해주세요.", Toast.LENGTH_LONG).show()
            } else {
                if (flag == 1) {
                    val intent = Intent(applicationContext, LoginActivity::class.java)

                    sign()
                    Log.v("::sooseol::",prof_img.toString())
                    startActivity(intent)
                    finish()
                }

            }
        }
    }

    // 통신(회원가입)
    fun sign() {
        val email = RequestBody.create(MediaType.parse("text/pain"), getEmail)
        val pwd = RequestBody.create(MediaType.parse("text/pain"), getPwd)
        val name = RequestBody.create(MediaType.parse("text/pain"), getName)
        val nickname = RequestBody.create(MediaType.parse("text/plain"), getNickname)
        val answer1 = RequestBody.create(MediaType.parse("text/plain"), question_q1Edit.text.toString())
        val answer2 = RequestBody.create(MediaType.parse("text/plain"), question_q2Edit.text.toString())
        val type = RequestBody.create(MediaType.parse("text/plain"), "일반")

//        val detailResponse = networkService!!.signup(email, pwd, answer1, answer2, nickname, name, type) // 회원가입할 때는 이미지 안보냄(null로 처리)

        val detailResponse = networkService!!.signup(email, pwd, answer1, answer2, nickname, name, type,prof_img)
        // Response 받은거
        detailResponse.enqueue(object : Callback<SignResponse> {
            override fun onFailure(call: Call<SignResponse>?, t: Throwable?) {
                Log.v(TAG, "가입 쀄일")
            }

            override fun onResponse(call: Call<SignResponse>, response: Response<SignResponse>) {
                if (response.isSuccessful) { //  가입 성공
                    ApplicationController.instance!!.makeToast("가입 완료해버렸다")
                    Log.v(TAG, "가입 성공~~~~~~")
                    flag = 1
                    if(prof_img==null){
                    }
                } else { // 가입 실패
                    if (response.code().toString() == "401") { //  이메일 또는 닉네임 중복\
                        flag = 0
                    }
                    Log.v(TAG, response.message())
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}