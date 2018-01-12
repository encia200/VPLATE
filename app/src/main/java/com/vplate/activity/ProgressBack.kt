package com.vplate.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by chosoomin on 2018. 1. 12..
 */
class ProgressBack(context : Context?) : AsyncTask<String, String, String>()  {
    var PD: ProgressDialog = ProgressDialog(context) // alert

    override fun onPostExecute(result: String?) {
        PD.dismiss()
    }

    override fun onPreExecute() {
//        PD = ProgressDialog.show(context, null, "Please Wait ...", true)
        PD.setCancelable(true)
    }

    override fun doInBackground(vararg params: String?): String {
        downloadFile("https://hyunho9304.s3.ap-northeast-2.amazonaws.com/1515688845611.mp4", "Sample.mp4")

        return params.toString()
    }

    fun downloadFile(fileURL : String?, fileName : String?) {
        try {
            val rootDir = (Environment.getExternalStorageDirectory().toString()
                    + File.separator + "Video")
            val rootFile = File(rootDir)
            rootFile.mkdir();
            var url = URL(fileURL)
            var c = url.openConnection() as HttpURLConnection
            c.requestMethod = "GET"
            c.doOutput = true
            c.connect()
            val f = FileOutputStream(File(rootFile, fileName))
            val inputStream = c.inputStream
            val buffer = ByteArray(1024)
            var len1 = 0


            len1 = inputStream.read(buffer)

            while (len1 > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
        } catch (e: IOException) {
            Log.d("Error....", e.toString());
        }
    }


}