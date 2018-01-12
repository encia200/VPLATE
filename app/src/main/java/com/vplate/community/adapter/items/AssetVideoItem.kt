package com.vplate.community.adapter.items

import android.content.res.AssetFileDescriptor
import com.squareup.picasso.Picasso
import com.volokh.danylo.video_player_manager.Config
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager
import com.volokh.danylo.video_player_manager.meta.MetaData
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView
import com.volokh.danylo.video_player_manager.utils.Logger
import com.vplate.community.adapter.holders.VideoViewHolder


class AssetVideoItem(private val mTitle: String, private val mAssetFileDescriptor: AssetFileDescriptor, videoPlayerManager: VideoPlayerManager<MetaData>, private val mImageLoader: Picasso, private val mImageResource: Int) : BaseVideoItem(videoPlayerManager) {

    override fun update(position: Int, viewHolder: VideoViewHolder, videoPlayerManager: VideoPlayerManager<*>) {
        if (SHOW_LOGS) Logger.v(TAG, "update, position " + position)

//        viewHolder.mTitle.text = mTitle // 수민
//        viewHolder.mCover.visibility = View.VISIBLE  // 수민
//        viewHolder.mLinearLayout.visibility=View.VISIBLE // 수민
//        mImageLoader.load(mImageResource).into(viewHolder.mCover) // 수민
    }


    override fun playNewVideo(currentItemMetaData: MetaData, player: VideoPlayerView, videoPlayerManager: VideoPlayerManager<MetaData>) {
        videoPlayerManager.playNewVideo(currentItemMetaData, player, mAssetFileDescriptor)
    }

    override fun stopPlayback(videoPlayerManager: VideoPlayerManager<*>) {
        videoPlayerManager.stopAnyPlayback()
    }

    override fun toString(): String {
        return javaClass.toString() + ", mTitle[" + mTitle + "]"
    }

    companion object {

        private val TAG = AssetVideoItem::class.java.simpleName
        private val SHOW_LOGS = Config.SHOW_LOGS
    }
}
