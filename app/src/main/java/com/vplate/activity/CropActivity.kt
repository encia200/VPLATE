package com.vplate.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.naver.android.helloyako.imagecrop.view.ImageCropView
import com.vplate.R
import java.io.File
import java.io.FileOutputStream

class CropActivity : Activity() {
    private var imageCropView: com.naver.android.helloyako.imagecrop.view.ImageCropView? = null
    private val positionInfo: FloatArray? = null
    var getTemplate: String? = null
    var b: Bitmap? = null
    var croppedFile: File? = null


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop)
        imageCropView = findViewById(R.id.image) as ImageCropView
        val intent: Intent = getIntent()!!
        getTemplate = intent.getStringExtra("Template") // 템플릿 번호
        val uri = intent.data // 이미지 Uri
        /*
            toodo 씬번호랑 아티클 넘버 받아오기
        */

        imageCropView!!.setImageFilePath(uri.toString())
        imageCropView!!.setAspectRatio(1, 1)

        findViewById(R.id.ratio11btn).setOnClickListener {
            Log.d(TAG, "click 1 : 1")
            if (isPossibleCrop(1, 1)) {
                imageCropView!!.setAspectRatio(1, 1)
            } else {
                //Toast.makeText(this@CropActivity, R.string.can_not_crop, Toast.LENGTH_SHORT).show()
            }
        }

        findViewById(R.id.ratio34btn).setOnClickListener {
            Log.d(TAG, "click 3 : 4")
            if (isPossibleCrop(3, 4)) {
                imageCropView!!.setAspectRatio(3, 4)
            } else {
                //Toast.makeText(this@CropActivity, R.string.can_not_crop, Toast.LENGTH_SHORT).show()
            }
        }

        findViewById(R.id.ratio43btn).setOnClickListener {
            Log.d(TAG, "click 4 : 3")
            if (isPossibleCrop(4, 3)) {
                imageCropView!!.setAspectRatio(4, 3)
            } else {
                //Toast.makeText(this@CropActivity, R.string.can_not_crop, Toast.LENGTH_SHORT).show()
            }
        }

        findViewById(R.id.ratio169btn).setOnClickListener {
            Log.d(TAG, "click 16 : 9")
            if (isPossibleCrop(16, 9)) {
                imageCropView!!.setAspectRatio(16, 9)
            } else {
                //Toast.makeText(this@CropActivity, R.string.can_not_crop, Toast.LENGTH_SHORT).show()
            }
        }

        findViewById(R.id.ratio916btn).setOnClickListener {
            Log.d(TAG, "click 9 : 16")
            if (isPossibleCrop(9, 16)) {
                imageCropView!!.setAspectRatio(9, 16)

            } else {
                //Toast.makeText(this@CropActivity, R.string.can_not_crop, Toast.LENGTH_SHORT).show()
            }
        }

        findViewById(R.id.crop_btn).setOnClickListener {
            // imageCropView!!.setAspectRatio(9, 16)
            if (!imageCropView!!.isChangingScale) {
                b = imageCropView!!.croppedImage
            }

        }
        findViewById(R.id.confirm_btn).setOnClickListener {
            if (b != null) {
                croppedFile = bitmapConvertToFile(b)
                val intent = Intent()
                intent.putExtra("croppedImagePath", croppedFile!!.path)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }


        }
    }

    private fun isPossibleCrop(widthRatio: Int, heightRatio: Int): Boolean {
        val bitmap = imageCropView!!.viewBitmap ?: return false
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        return if (bitmapWidth < widthRatio && bitmapHeight < heightRatio) {
            false
        } else {
            true
        }
    }

    fun bitmapConvertToFile(bitmap: Bitmap?): File? {
        var fileOutputStream: FileOutputStream? = null
        var bitmapFile: File? = null


        try {
            val file = File(Environment.getExternalStoragePublicDirectory("image_crop_sample"), "")
            if (!file.exists()) {
                file.mkdir()
            }


            //  bitmapFile = File(file, "IMG_" +"s"+ ".jpg")
            bitmapFile = File(file, getTemplate + ".jpg")

            fileOutputStream = FileOutputStream(bitmapFile)
            bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            MediaScannerConnection.scanFile(this, arrayOf(bitmapFile.absolutePath), null, object : MediaScannerConnection.MediaScannerConnectionClient {
                override fun onMediaScannerConnected() {

                }

                override fun onScanCompleted(path: String, uri: Uri) {
                    runOnUiThread { Toast.makeText(this@CropActivity, "file saved", Toast.LENGTH_LONG).show() }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush()
                    fileOutputStream.close()
                } catch (e: Exception) {
                }

            }
        }

        return bitmapFile
    }

    /*  fun onClickSaveButton(v: View) {
          imageCropView!!.saveState()
          val restoreButton = findViewById(R.id.restore_btn)
          if (!restoreButton.isEnabled) {
              restoreButton.isEnabled = true
          }
      }

      fun onClickRestoreButton(v: View) {
          imageCropView!!.restoreState()
      }*/

    companion object {
        val TAG = "CropActivity"
    }

}
