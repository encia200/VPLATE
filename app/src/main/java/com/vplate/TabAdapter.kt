package com.vplate

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.vplate.Fragment.*

class TabAdapter(fm : FragmentManager?) : FragmentStatePagerAdapter(fm) {

    var tabCount : Int = 0

    var firstTab : FirstFragment?= null
    var secondTab : SecondFragment?= null
    var homeTab : HomeFragment?= null
    var communityTab: CommunityFragment?= null
    var fifthTab : FifthFragment?= null

    constructor(fm : FragmentManager?, tabCount : Int) : this(fm){
        this.tabCount = tabCount
        this.firstTab = FirstFragment()
        this.secondTab = SecondFragment()
        this.homeTab = HomeFragment()
        this.communityTab = CommunityFragment()
        this.fifthTab = FifthFragment()
    }

    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> {
                val bundle = Bundle()
                firstTab!!.arguments = bundle

                return firstTab
            }
            1 -> {
                val bundle = Bundle()

                return secondTab
            }
            2 -> {
                val bundle = Bundle()
                homeTab!!.arguments = bundle

                return homeTab
            }
            3 -> {
                return communityTab
            }
            4 -> {
                return fifthTab
            }
        }

        return null
    }

    override fun getCount(): Int = tabCount
}