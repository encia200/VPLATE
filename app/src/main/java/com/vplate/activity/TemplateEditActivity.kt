package com.vplate.activity

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.CursorLoader
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.naver.android.helloyako.imagecrop.util.BitmapLoadUtils
import com.vplate.HorizontalAdapter
import com.vplate.Network.ApplicationController
import com.vplate.Network.CommonData
import com.vplate.Network.Get.HorizontalData
import com.vplate.Network.Get.Response.TemplateIdResponse
import com.vplate.Network.NetworkService
import com.vplate.Network.NothingResponse
import com.vplate.R
import com.vplate.activity.CropActivity
import kotlinx.android.synthetic.main.activity_template_edit.*
import life.knowledge4.videotrimmer.utils.FileUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TemplateEditActivity : AppCompatActivity(), View.OnClickListener {

    private var mHorizontalView: RecyclerView? = null
    private var mAdapter: HorizontalAdapter? = null
    private var mLayoutManager: LinearLayoutManager? = null
    internal var data: ArrayList<HorizontalData>? = null
    internal var mImageUri: Uri? = null
    internal var imageWidth: Int = 0
    internal var imageHeight: Int = 0
    var filePathUri: Uri? = null
    private var networkService: NetworkService? = null // 넽웕 썰비스
    var ScenePhoto : ArrayList<String>? = ArrayList()

    //
//    var arrayScene : ArrayList<HorizontalData>? = null
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_edit)
        networkService = ApplicationController.instance!!.networkService // 통신
        imageWidth = 1000
        imageHeight = 1000

        // 수민
        var templateId = intent.getIntExtra("templateId", 0)
        // 수민

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // 설치시 동의 안했을 때 21 이상
            if (packageManager.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, packageName) == PackageManager.PERMISSION_GRANTED) {
//                template_edit_photo_upload!!.setOnClickListener(this)
//                template_edit_video_upload!!.setOnClickListener(this)
            }
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), MAIN_ACTIVITY_REQUEST_STORAGE) // 권한 요청
        } else { // 설치시 동의 21이하
//            template_edit_video_upload!!.setOnClickListener(this)
//            template_edit_photo_upload!!.setOnClickListener(this)
        }

        // 수민
//        scenePhoto(100)//템플릿 아이디 받아와서 이미지 URL받아오기
        scenePhoto(templateId)

        // 수민

        // RecyclerView binding
        mHorizontalView = findViewById(R.id.template_timeline_recycler_view) as RecyclerView

        // init LayoutManager
        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL // 기본값이 VERTICAL

        // setLayoutManager
        mHorizontalView!!.layoutManager = mLayoutManager

        // 여기부터 서버에서 받아오는 코드

        // init Adapter
        mAdapter = HorizontalAdapter(ScenePhoto)
        // init Data
        data = ArrayList() // response!!.body().data.객체배열
//        data!!.add(HorizontalData("aaa",R.mipmap.ic_launcher))
//        data!!.add(HorizontalData("bbb",R.mipmap.ic_launcher))
//        data!!.add(HorizontalData("ccc",R.mipmap.ic_launcher))


        /*for(i in 1..ScenePhoto!!.size){
            data!!.add(HorizontalAdapter.HorizontalData("aaa"+i,R.mipmap.ic_launcher))
        }*/
        // set Data
//        mAdapter!!.setData(data!!)
        mAdapter!!.setOnItemClick(this)
        // set Adapter
        mHorizontalView!!.adapter = mAdapter

        buttonEvent()//비디오, 사진, 텍스트 편집 선택기

    }

    override fun onClick(v: View) {
        val idx = mHorizontalView!!.getChildAdapterPosition(v) // 씬 번호

//        when (v.id) { // 동적으로 뷰 생성하게되면 없어질 예정
//            R.id.template_edit_video_upload -> {
//                pickVideoFromGallery()
//            }
//            R.id.template_edit_photo_upload -> {
//                pickImageFromGallery()
//            }
//        }

        when(idx){
            0->{
//                Glide.with(this)
//                        .load(ScenePhoto!!.get(0))
//                        .into(template_edit_scene)

                get()
            }

        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MAIN_ACTIVITY_REQUEST_STORAGE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //pickVideoFromGallery() // 이거거나 리스너 달기
//                template_edit_photo_upload!!.setOnClickListener(this)
//                template_edit_video_upload!!.setOnClickListener(this)
            }
            REQUEST_STORAGE_READ_ACCESS_PERMISSION -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //pickVideoFromGallery() // 이거거나 리스너 달기
//                template_edit_photo_upload!!.setOnClickListener(this)
//                template_edit_video_upload!!.setOnClickListener(this)
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ACTION_REQUEST_GALLERY) { // 이미지

                var filePath: String? = ""
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    filePath = getRealPathFromURI_API19(this, data.data)
                } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB && Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                    filePath = getRealPathFromURI_API11to18(this, data.data)
                } else {
                    filePath = getRealPathFromURI_BelowAPI11(this, data.data)
                }

                filePathUri = Uri.parse(filePath)
                loadAsync(filePathUri)
            } else if (requestCode == REQUEST_VIDEO_TRIMMER) { // 비디오
                val selectedUri = data.data
                if (selectedUri != null) {
                    startTrimActivity(selectedUri)
                } else {
                    // 비디오 편집 실패
                }
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        startActivityForResult(chooser, ACTION_REQUEST_GALLERY)
    }

    private fun pickVideoFromGallery() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, getString(R.string.permission_read_storage_rationale), REQUEST_STORAGE_READ_ACCESS_PERMISSION)
        } else {
            val intent = Intent()
            intent.setTypeAndNormalize("video/*")
            intent.action = Intent.ACTION_GET_CONTENT
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(Intent.createChooser(intent, getString(R.string.label_select_video)), REQUEST_VIDEO_TRIMMER)
        }
    }


    private fun setImageURI(uri: Uri?, bitmap: Bitmap): Boolean {

        Log.d(TAG, "image size: " + bitmap.width + "x" + bitmap.height)

        mImageUri = uri

        return true
    }

    private fun loadAsync(uri: Uri?) {
        Log.i(TAG, "loadAsync: " + uri!!)
        val task = DownloadAsync()
        task.execute(uri)
    }

    internal inner class DownloadAsync : AsyncTask<Uri, Void, Bitmap>(), DialogInterface.OnCancelListener {

        var mProgress: ProgressDialog? = null
        private var mUri: Uri? = null

        override fun onPreExecute() {
            super.onPreExecute()

            mProgress = ProgressDialog(this@TemplateEditActivity)
            mProgress!!.isIndeterminate = true
            mProgress!!.setCancelable(true)
            mProgress!!.setMessage("Loading image...")
            mProgress!!.setOnCancelListener(this)
            mProgress!!.show()
        }

        override fun doInBackground(vararg params: Uri): Bitmap? {
            mUri = params[0]

            var bitmap: Bitmap? = null

            bitmap = BitmapLoadUtils.decode(mUri!!.toString(), imageWidth, imageHeight, true)
            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)

            if (mProgress!!.window != null) {
                mProgress!!.dismiss()
            }

            if (result != null) {
                setImageURI(mUri, result)
            } else {
                Toast.makeText(this@TemplateEditActivity, "Failed to load image " + mUri!!, Toast.LENGTH_SHORT).show()
            }
            val intent00 = Intent(applicationContext, CropActivity::class.java)
            intent00.putExtra("Template", "1")
            intent00.putExtra("Scene", "1")
            intent00.putExtra("ArtNum", "3")
            intent00.data = mImageUri
            startActivity(intent00)
        }

        override fun onCancel(dialog: DialogInterface) {
            Log.i(TAG, "onProgressCancel")
            this.cancel(true)
        }

        override fun onCancelled() {
            super.onCancelled()
            Log.i(TAG, "onCancelled")
        }

    }

    companion object {

        val TAG = "MainActivity"

        //이미지
        private val MAIN_ACTIVITY_REQUEST_STORAGE = Activity.RESULT_FIRST_USER
        private val ACTION_REQUEST_GALLERY = 99

        //비디오
        private val REQUEST_VIDEO_TRIMMER = 0x01
        private val REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101
        internal val EXTRA_VIDEO_PATH = "EXTRA_VIDEO_PATH"

        @TargetApi(Build.VERSION_CODES.KITKAT)
        fun getRealPathFromURI_API19(context: Context, uri: Uri): String {
            var filePath = ""
            val wholeID = DocumentsContract.getDocumentId(uri)

            // Split at colon, use second item in the array
            val id = wholeID.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[1]

            val column = arrayOf(MediaStore.Images.Media.DATA)

            // where id is equal to
            val sel = MediaStore.Images.Media._ID + "=?"

            val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, arrayOf(id), null)

            val columnIndex = cursor!!.getColumnIndex(column[0])

            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex)
            }
            cursor.close()
            return filePath
        }

        fun getRealPathFromURI_API11to18(context: Context, contentUri: Uri): String? {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            var result: String? = null

            val cursorLoader = CursorLoader(
                    context,
                    contentUri, proj, null, null, null)
            val cursor = cursorLoader.loadInBackground()

            if (cursor != null) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                result = cursor.getString(columnIndex)
            }
            return result
        }

        fun getRealPathFromURI_BelowAPI11(context: Context, contentUri: Uri): String {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)
        }
    }


    //비디오
    private fun startTrimActivity(uri: Uri) {
        val intent = Intent(this, TrimmerActivity::class.java)
        intent.putExtra(EXTRA_VIDEO_PATH, FileUtils.getPath(this, uri))
        startActivity(intent)
    }

    /**
     * Requests given permission.
     * If the permission has been denied previously, a Dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private fun requestPermission(permission: String, rationale: String, requestCode: Int) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.permission_title_rationale))
            builder.setMessage(rationale)
            builder.setPositiveButton(getString(R.string.label_ok)) { dialog, which -> ActivityCompat.requestPermissions(this@TemplateEditActivity, arrayOf(permission), requestCode) }
            builder.setNegativeButton(getString(R.string.label_cancel), null)
            builder.show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        }
    }

    // 영상 제작하기 (씬정보)
    fun scenePhoto(temp_num:Int) {
        val sceneResponse = networkService!!.ScenePhoto(CommonData.loginResponse!!.token, temp_num)

        sceneResponse.enqueue(object : Callback<TemplateIdResponse> {
            override fun onResponse(call: Call<TemplateIdResponse>?, response: Response<TemplateIdResponse>?) {
                if (response!!.isSuccessful) {
                    ScenePhoto = response!!.body().data
                    mAdapter = HorizontalAdapter(ScenePhoto)
                    mAdapter!!.setOnItemClick(this@TemplateEditActivity)
                    mHorizontalView = findViewById(R.id.template_timeline_recycler_view) as RecyclerView
                    mHorizontalView!!.adapter = mAdapter
                } else {
                    ApplicationController.instance!!.makeToast("못 받음ㅠ")
                }
            }

            override fun onFailure(call: Call<TemplateIdResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 오류")
            }
        })
    }

    fun buttonEvent(){
        down_btn01.setOnClickListener {
            template_edit_video_select.visibility = View.VISIBLE
            template_edit_photo_select.visibility = View.GONE
            template_edit_text_select.visibility = View.GONE
        }
        down_btn02.setOnClickListener {
            template_edit_video_select.visibility = View.GONE
            template_edit_photo_select.visibility = View.VISIBLE
            template_edit_text_select.visibility = View.GONE
        }
        down_btn03.setOnClickListener {
            template_edit_video_select.visibility = View.GONE
            template_edit_photo_select.visibility = View.GONE
            template_edit_text_select.visibility = View.VISIBLE
        }
    }

    //
    fun get() {
        val response = networkService!!.nothing(CommonData.loginResponse!!.token, 94, ScenePhoto!!.get(0))

        response.enqueue(object : Callback<NothingResponse> {
            override fun onFailure(call: Call<NothingResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 실패")
            }

            override fun onResponse(call: Call<NothingResponse>?, response: Response<NothingResponse>?) {
                if (response!!.isSuccessful) {
                    var array : ArrayList<Double>? = null
                    array = response!!.body().data

                    var i = 0

                    var t : Double? = null

                    // 1 text 2 photo - 1-  1:1           2 -4:3               3 -16:9
                    // //3 video

                    var videoCount = 0
                    var photoCount = 0
                    var textCount = 0

                    for (i in array) {
                        if (i != null) {
                            t = i - i.toInt().toDouble()
                            Log.v("dfdfdfdf345", t!!.toFloat().toString())
//                            Log.v("dfdfdfdf", i.toInt().toFloat().toString())

                            if (i.toInt() == 1) {
                                textCount++

                                var textLength = t * 100

                                Log.v("::getText 길이", textLength.toInt().toString())
                            }
                            else if (i.toInt() == 2) {
                                photoCount++

                                var photoRatio = t * 100
                                var intPhoto = photoRatio.toInt()

                                Log.v("::getPhoto", photoRatio.toString())

                                if (intPhoto.equals(1)) {
                                    Log.v("::getPhoto 비율", "1:1")
                                }
                                else if (intPhoto.equals(2)) {
                                    Log.v("::getPhoto 비율", "4:3")
                                }
                                else if (intPhoto.equals(3)) {
                                    Log.v("::getPhoto 비율", "16:9")
                                }
                            }
                            else if (i.toInt() == 3) {
                                videoCount++

                                var videoLength = t * 100

                                Log.v("::getVideo 초", videoLength.toInt().toString())
                            }
                        }

                    }

                    ApplicationController.instance!!.makeToast("text개수" + textCount.toString() + " / photo개수" + photoCount.toString() + " / video 개수" + videoCount.toString())

                }
                else {
                    ApplicationController.instance!!.makeToast("못받음")
                }
            }
        })
    }

}