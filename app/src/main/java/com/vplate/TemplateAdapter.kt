package com.vplate

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vplate.Network.Post.TemplateData

class TemplateAdapter(var dataList: ArrayList<TemplateData>?) : RecyclerView.Adapter<TemplateViewHolder>() {

    private var onItemClick: View.OnClickListener? = null

    override fun onBindViewHolder(holder: TemplateViewHolder?, position: Int) {
        //holder!!.templateImage.setImageBitmap(retriveVideoFrameFromVideo(dataList!!.get(position).template_video))
        holder!!.templateImage.setImageResource(R.mipmap.ic_launcher)
        holder.templateTitle.text = dataList!!.get(position).template_title
        holder.templateContent.text = dataList!!.get(position).template_content
        holder.templateTime.text = dataList!!.get(position).template_length.toString()
        if (dataList!!.get(position).template_type.equals("2"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_2)
        else if (dataList!!.get(position).template_type.equals("3"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_3)
        else if (dataList!!.get(position).template_type.equals("4"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_4)
        else if (dataList!!.get(position).template_type.equals("5"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_5)
        else if (dataList!!.get(position).template_type.equals("6"))
            holder.templateCategory.setImageResource(R.drawable.cate_icon_6)
        else
            holder.templateCategory.setImageResource(R.drawable.cate_icon_6)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TemplateViewHolder {
        val mainView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_template, parent, false)
        mainView.setOnClickListener(onItemClick)

        return TemplateViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    fun setOnItemClickListener(l: View.OnClickListener) {
        onItemClick = l
    }

    @Throws(Throwable::class)
    fun retriveVideoFrameFromVideo(videoPath: String): Bitmap? {
        var bitmap: Bitmap? = null
        var mediaMetadataRetriever: MediaMetadataRetriever? = null
        try {
            mediaMetadataRetriever = MediaMetadataRetriever()
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, HashMap())
            else
                mediaMetadataRetriever.setDataSource(videoPath)

            bitmap = mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST)
        } catch (e: Exception) {
            e.printStackTrace()
            throw Throwable(
                    "Exception in retriveVideoFrameFromVideo(String videoPath)" + e.message)

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release()
            }
        }
        return bitmap
    }
}