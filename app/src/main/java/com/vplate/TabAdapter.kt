package com.vplate

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.vplate.Fragment.*

class TabAdapter(fm : FragmentManager?) : FragmentStatePagerAdapter(fm) {

    var tabCount : Int = 0

    var homeTab : HomeFragment?= null
    var videoTab : FirstFragment?= null
    var likeTab : PickFragment?= null
    var communityTab: CommunityFragment?= null
    var mypageTab : MyPageFragment?= null

    constructor(fm : FragmentManager?, tabCount : Int) : this(fm){
        this.tabCount = tabCount
        this.videoTab = FirstFragment()
        this.likeTab = PickFragment()
        this.homeTab = HomeFragment()
        this.communityTab = CommunityFragment()
        this.mypageTab = MyPageFragment()
    }

    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> {
                val bundle = Bundle()
                homeTab!!.arguments = bundle

                return homeTab
            }
            1 -> {
                val bundle = Bundle()

                return videoTab
            }
            2 -> {
                val bundle = Bundle()
                likeTab!!.arguments = bundle

                return likeTab
            }
            3 -> {
                return communityTab
            }
            4 -> {
                return mypageTab
            }
        }

        return null
    }

    override fun getCount(): Int = tabCount
}