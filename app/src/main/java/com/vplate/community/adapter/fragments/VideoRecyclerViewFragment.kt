package com.vplate.community.adapter.fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import com.volokh.danylo.video_player_manager.Config
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator
import com.volokh.danylo.visibility_utils.scroll_utils.ItemsPositionGetter
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter
import com.vplate.R
import com.vplate.activity.CommunityActivity
import com.vplate.community.adapter.VideoRecyclerViewAdapter
import com.vplate.community.adapter.items.BaseVideoItem
import com.vplate.community.adapter.items.DirectLinkVideoItem
import com.vplate.community.adapter.items.ItemFactory
import kotlinx.android.synthetic.main.fragment_video_recycler_view.*
import java.io.IOException


/**
 * This fragment shows of how to use [VideoPlayerManager] with a RecyclerView.
 */
class VideoRecyclerViewFragment : Fragment() {

    private val mList = ArrayList<BaseVideoItem>()

    /**
     * Only the one (most visible) view should be active (and playing).
     * To calculate visibility of views we use [SingleListViewItemActiveCalculator]
     */
    private val mVideoVisibilityCalculator = SingleListViewItemActiveCalculator(DefaultSingleItemCalculatorCallback(), mList)

    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mFloatingbackBtn: FloatingActionButton? = null
    private var mFloatingrankingBtn: FloatingActionButton? = null
    private var mFloatingmyvideoBtn: FloatingActionButton? = null
    /**
     * ItemsPositionGetter is used by [ListItemsVisibilityCalculator] for getting information about
     * items position in the RecyclerView and LayoutManager
     */
    private var mItemsPositionGetter: ItemsPositionGetter? = null

    /**
     * Here we use [SingleVideoPlayerManager], which means that only one video playback is possible.
     */
    private val mVideoPlayerManager = SingleVideoPlayerManager(PlayerItemChangeListener { })

    private var mScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        try {
            mList.add(DirectLinkVideoItem("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", mVideoPlayerManager, context))
            mList.add(DirectLinkVideoItem("https://hyunho9304.s3.ap-northeast-2.amazonaws.com/1515258231423.mp4", mVideoPlayerManager, context))
            mList.add(DirectLinkVideoItem("https://hyunho9304.s3.ap-northeast-2.amazonaws.com/1515258316509.mp4", mVideoPlayerManager, context))
            mList.add(DirectLinkVideoItem("https://hyunho9304.s3.ap-northeast-2.amazonaws.com/1515258520682.mp4", mVideoPlayerManager, context))
            mList.add(DirectLinkVideoItem("https://hyunho9304.s3.ap-northeast-2.amazonaws.com/1515258871020.mp4", mVideoPlayerManager, context))
            mList.add(DirectLinkVideoItem("https://hyunho9304.s3.ap-northeast-2.amazonaws.com/1515258630793.mp4", mVideoPlayerManager, context))
            mList.add(DirectLinkVideoItem("https://hyunho9304.s3.ap-northeast-2.amazonaws.com/1515258621607.mp4", mVideoPlayerManager, context))

            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.video_sample_1_pic, activity, mVideoPlayerManager))
            mList.add(ItemFactory.createItemFromAsset("video_sample_3.mp4", R.drawable.video_sample_3_pic, activity, mVideoPlayerManager))
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.video_sample_1_pic, activity, mVideoPlayerManager))
            mList.add(ItemFactory.createItemFromAsset("video_sample_3.mp4", R.drawable.video_sample_3_pic, activity, mVideoPlayerManager))
            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.video_sample_1_pic, activity, mVideoPlayerManager))
            mList.add(ItemFactory.createItemFromAsset("video_sample_3.mp4", R.drawable.video_sample_3_pic, activity, mVideoPlayerManager))

        } catch (e: IOException) {
            throw RuntimeException(e)
        }

        val rootView = inflater!!.inflate(R.layout.fragment_video_recycler_view, container, false)

        mRecyclerView = rootView.findViewById(R.id.recycler_view) as RecyclerView
        mFloatingbackBtn = rootView.findViewById(R.id.community_backbtn) as FloatingActionButton
        mFloatingbackBtn!!.setOnClickListener {
            CommunityActivity.dialogData.setDialogData(true)
        }
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        //mRecyclerView!!.setHasFixedSize(true);
        mRecyclerView!!.setBackgroundColor(0x00ffffff)

        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.layoutManager = mLayoutManager


        val videoRecyclerViewAdapter = VideoRecyclerViewAdapter(mVideoPlayerManager, activity, mList)

        mRecyclerView!!.adapter = videoRecyclerViewAdapter
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, scrollState: Int) {
                mScrollState = scrollState
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE && !mList.isEmpty()) {

                    mVideoVisibilityCalculator.onScrollStateIdle(
                            mItemsPositionGetter,
                            mLayoutManager!!.findFirstVisibleItemPosition(),
                            mLayoutManager!!.findLastVisibleItemPosition())
                }
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    community_myvideo.show()
                    community_ranking.show()
                    community_backbtn.show()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && community_ranking!!.isShown()) {
                    community_ranking.hide()
                    community_myvideo.hide()
                    community_backbtn.hide()
                }
                if (!mList.isEmpty()) {
                    mVideoVisibilityCalculator.onScroll(
                            mItemsPositionGetter,
                            mLayoutManager!!.findFirstVisibleItemPosition(),
                            mLayoutManager!!.findLastVisibleItemPosition() - mLayoutManager!!.findFirstVisibleItemPosition() + 1,
                            mScrollState)
                }
            }
        })
        mItemsPositionGetter = RecyclerViewItemPositionGetter(mLayoutManager, mRecyclerView)


        return rootView
    }

    override fun onResume() {
        super.onResume()
        if (!mList.isEmpty()) {
            // need to call this method from list view handler in order to have filled list

            mRecyclerView!!.post {
                mVideoVisibilityCalculator.onScrollStateIdle(
                        mItemsPositionGetter,
                        mLayoutManager!!.findFirstVisibleItemPosition(),
                        mLayoutManager!!.findLastVisibleItemPosition())
            }
        }
    }

    override fun onStop() {
        super.onStop()
        // we have to stop any playback in onStop
        mVideoPlayerManager.resetMediaPlayer()
    }

    companion object {

        private val SHOW_LOGS = Config.SHOW_LOGS
        private val TAG = VideoRecyclerViewFragment::class.java.simpleName
    }
}