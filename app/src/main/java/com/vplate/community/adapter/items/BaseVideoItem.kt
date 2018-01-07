package com.vplate.community.adapter.items

import android.graphics.Rect
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.volokh.danylo.video_player_manager.manager.VideoItem
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager
import com.volokh.danylo.video_player_manager.meta.CurrentItemMetaData
import com.volokh.danylo.video_player_manager.meta.MetaData
import com.volokh.danylo.video_player_manager.ui.MediaPlayerWrapper
import com.volokh.danylo.video_player_manager.utils.Logger
import com.volokh.danylo.visibility_utils.items.ListItem
import com.vplate.R
import com.vplate.community.adapter.holders.VideoViewHolder

abstract class BaseVideoItem protected constructor(private val mVideoPlayerManager: VideoPlayerManager<MetaData>) : VideoItem, ListItem {

    /**
     * An object that is filled with values when [.getVisibilityPercents] method is called.
     * This object is local visible rect filled by [View.getLocalVisibleRect]
     */

    private val mCurrentViewRect = Rect()

    /**
     * This method needs to be called when created/recycled view is updated.
     * Call it in
     * 1. [android.widget.ListAdapter.getView]
     * 2. [RecyclerView.Adapter.onBindViewHolder]
     */
    abstract fun update(position: Int, view: VideoViewHolder, videoPlayerManager: VideoPlayerManager<*>)

    /**
     * When this item becomes active we start playback on the video in this item
     */
    override fun setActive(newActiveView: View, newActiveViewPosition: Int) {

        val viewHolder = newActiveView.tag as VideoViewHolder

        YoYo.with(Techniques.FadeIn).playOn(viewHolder.mCardView)
        viewHolder.mCover.visibility = View.INVISIBLE
        //viewHolder.mCardView.setVisibility(VISIBLE);
        playNewVideo(CurrentItemMetaData(newActiveViewPosition, newActiveView), viewHolder.mPlayer, mVideoPlayerManager)
    }

    /**
     * When this item becomes inactive we stop playback on the video in this item.
     */
    override fun deactivate(currentView: View, position: Int) {
        stopPlayback(mVideoPlayerManager)
    }

    fun createView(parent: ViewGroup, screenWidth: Int, screenHeight: Int): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.community_video_item, parent, false)

        /*val layoutParams: RelativeLayout.LayoutParams = view.getLayoutParams() as RelativeLayout.LayoutParams*/
        /*layoutParams.height = screenWidth
        layoutParams.width = screenWidth*/

        val videoViewHolder = VideoViewHolder(view)
        view.setTag(videoViewHolder)

        videoViewHolder.mPlayer.addMediaPlayerListener(object : MediaPlayerWrapper.MainThreadMediaPlayerListener {
            override fun onVideoSizeChangedMainThread(width: Int, height: Int) {}

            override fun onVideoPreparedMainThread() {
                // When video is prepared it's about to start playback. So we hide the cover
                videoViewHolder.mCover.visibility = View.INVISIBLE
            }

            override fun onVideoCompletionMainThread() {}

            override fun onErrorMainThread(what: Int, extra: Int) {}

            override fun onBufferingUpdateMainThread(percent: Int) {}

            override fun onVideoStoppedMainThread() {
                // Show the cover when video stopped
                YoYo.with(Techniques.FadeOut).playOn(videoViewHolder.mCardView)

                Handler().postDelayed({
                    //videoViewHolder.mCardView.setVisibility(View.INVISIBLE);
                    videoViewHolder.mCover.visibility = VISIBLE
                }, 1000)

            }
        })
        return view
    }

    /**
     * This method calculates visibility percentage of currentView.
     * This method works correctly when currentView is smaller then it's enclosure.
     *
     * @param currentView - view which visibility should be calculated
     * @return currentView visibility percents
     */
    override fun getVisibilityPercents(currentView: View): Int {
        if (SHOW_LOGS) Logger.v(TAG, ">> getVisibilityPercents currentView " + currentView)

        var percents = 100

        currentView.getLocalVisibleRect(mCurrentViewRect)
        if (SHOW_LOGS)
            Logger.v(TAG, "getVisibilityPercents mCurrentViewRect top " + mCurrentViewRect.top + ", left " + mCurrentViewRect.left + ", bottom " + mCurrentViewRect.bottom + ", right " + mCurrentViewRect.right)

        val height = currentView.height
        if (SHOW_LOGS) Logger.v(TAG, "getVisibilityPercents height " + height)

        if (viewIsPartiallyHiddenTop()) {
            // view is partially hidden behind the top edge
            percents = (height - mCurrentViewRect.top) * 100 / height
        } else if (viewIsPartiallyHiddenBottom(height)) {
            percents = mCurrentViewRect.bottom * 100 / height
        }

        setVisibilityPercentsText(currentView, percents)
        if (SHOW_LOGS) Logger.v(TAG, "<< getVisibilityPercents, percents " + percents)

        return percents
    }

    private fun setVisibilityPercentsText(currentView: View, percents: Int) {
        if (SHOW_LOGS) Logger.v(TAG, "setVisibilityPercentsText percents " + percents)
        val videoViewHolder = currentView.tag as VideoViewHolder
        val percentsText = "Visibility percents: " + percents.toString()

        //videoViewHolder.mVisibilityPercents.setText(percentsText);
    }

    private fun viewIsPartiallyHiddenBottom(height: Int): Boolean {
        return mCurrentViewRect.bottom > 0 && mCurrentViewRect.bottom < height
    }

    private fun viewIsPartiallyHiddenTop(): Boolean {
        return mCurrentViewRect.top > 0
    }

    companion object {

        private val SHOW_LOGS = false
        private val TAG = BaseVideoItem::class.java.simpleName
    }
}
