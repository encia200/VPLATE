package com.vplate.community.adapter.items

import android.app.Activity

import com.squareup.picasso.Picasso
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager
import com.volokh.danylo.video_player_manager.meta.MetaData

import java.io.IOException

object ItemFactory {

    @Throws(IOException::class)
    fun createItemFromAsset(assetName: String, imageResource: Int, activity: Activity, videoPlayerManager: VideoPlayerManager<MetaData>): BaseVideoItem {
        return AssetVideoItem(assetName, activity.assets.openFd(assetName), videoPlayerManager, Picasso.with(activity), imageResource)
    }
}
