package com.vplate.community.adapter.holders


import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView
import com.vplate.R

class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @JvmField val mPlayer: VideoPlayerView
    @JvmField val mTitle: TextView
    @JvmField val mCover: ImageView
    //public final TextView mVisibilityPercents;
    @JvmField val mCardView: CardView
    @JvmField val mLinearLayout:LinearLayout

    init {
        mPlayer = view.findViewById(R.id.player) as VideoPlayerView
        mTitle = view.findViewById(R.id.title) as TextView
        mCover = view.findViewById(R.id.cover) as ImageView
        //  mVisibilityPercents = (TextView) view.findViewById(R.id.visibility_percents);
        mCardView = view.findViewById(R.id.card_view) as CardView
        mLinearLayout = view.findViewById(R.id.community_bottomlayout) as LinearLayout
        mCardView!!.setBackgroundColor(0x00ffffff)

    }
}