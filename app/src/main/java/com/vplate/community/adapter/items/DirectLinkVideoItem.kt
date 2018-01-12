package com.vplate.community.adapter.items

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.os.Build
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager
import com.volokh.danylo.video_player_manager.meta.MetaData
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView
import com.vplate.community.adapter.holders.VideoViewHolder

class DirectLinkVideoItem(private val mDirectUrl: String, videoPlayerManager: VideoPlayerManager<*>, private val context: Context) : BaseVideoItem(videoPlayerManager as VideoPlayerManager<MetaData>) {
    override fun update(position: Int, viewHolder: VideoViewHolder, videoPlayerManager: VideoPlayerManager<*>) {
//        viewHolder.mLinearLayout.visibility = View.VISIBLE // 수민
//        viewHolder.mCover.visibility = View.VISIBLE // 수민
        //viewHolder.mCover.setImageBitmap(retriveVideoFrameFromVideo(mDirectUrl))
    }

    override fun playNewVideo(currentItemMetaData: MetaData, player: VideoPlayerView, videoPlayerManager: VideoPlayerManager<MetaData>) {
        videoPlayerManager.playNewVideo(currentItemMetaData, player, mDirectUrl)
    }

    override fun stopPlayback(videoPlayerManager: VideoPlayerManager<*>) {
        videoPlayerManager.stopAnyPlayback()
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
