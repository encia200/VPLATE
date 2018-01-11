package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.vplate.R
import com.vplate.TabAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_home.view.*

class MainActivity : AppCompatActivity() {

    private val COMMUNITYACTIVITY = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 탭 추가
        main_tab.addTab(main_tab.newTab()
                .setIcon(R.drawable.home_g))
        main_tab.addTab(main_tab.newTab()
                .setIcon(R.drawable.my_video_g))
        main_tab.addTab(main_tab.newTab()
                .setIcon(R.drawable.ddip_g))
        main_tab.addTab(main_tab.newTab()
                .setIcon(R.drawable.community_g))
        main_tab.addTab(main_tab.newTab()
                .setIcon(R.drawable.my_page_g))

        var tabAdapter = TabAdapter(supportFragmentManager, main_tab.tabCount)

        main_viewPager.adapter = tabAdapter

        main_viewPager.currentItem = 0
        main_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_tab))

        main_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                main_viewPager.currentItem = tab!!.position

                when (tab.position) {
                    0 -> {
                        tab.setCustomView(R.layout.tab_home)
                    }
                    1 -> {
                        tab.setIcon(R.drawable.my_video_g)
                    }
                    2 -> {
                        tab.setIcon(R.drawable.ddip_g)
                    }
                    3 -> {
                        tab.setIcon(R.drawable.community_g)
                    }
                    4 -> {
                        tab.setIcon(R.drawable.my_page_g)
                    }
                }

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                main_viewPager.currentItem = tab!!.position

                when (tab.position) {
                    0 -> {
                        tab.setCustomView(R.layout.tab_home)
                        tab.customView!!.homeIcon.setImageResource(R.drawable.home_o)
                    }
                    1 -> {
                        tab.setIcon(R.drawable.two_my_video_o)
                    }
                    2 -> {
                        tab.setIcon(R.drawable.two_ddip_o)
                    }
                    3 -> {
                        tab.setIcon(R.drawable.two_community_o)

                        val intent = Intent(applicationContext, CommunityActivity::class.java)

                        startActivityForResult(intent, COMMUNITYACTIVITY)
                    }
                    4 -> {
                        tab.setIcon(R.drawable.two_my_page_o)
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode.equals(COMMUNITYACTIVITY)) {
            main_viewPager.setCurrentItem(2, false)
        }
    }
}