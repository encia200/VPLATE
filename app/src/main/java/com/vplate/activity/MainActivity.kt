package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.vplate.R
import com.vplate.TabAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val COMMUNITYACTIVITY = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var tabView1 : View = layoutInflater.inflate(R.layout.tab_icon,null)
        var tabView2 : View = layoutInflater.inflate(R.layout.tab_icon,null)
        var tabView3 : View = layoutInflater.inflate(R.layout.tab_icon,null)
        var tabView4 : View = layoutInflater.inflate(R.layout.tab_icon,null)
        var tabView5 : View = layoutInflater.inflate(R.layout.tab_icon,null)

        var tabImg1 : ImageView = tabView1.findViewById(R.id.tab_icon) as ImageView
        var tabImg2 : ImageView = tabView2.findViewById(R.id.tab_icon) as ImageView
        var tabImg3 : ImageView = tabView3.findViewById(R.id.tab_icon) as ImageView
        var tabImg4 : ImageView = tabView4.findViewById(R.id.tab_icon) as ImageView
        var tabImg5 : ImageView = tabView5.findViewById(R.id.tab_icon) as ImageView

        tabImg1.setImageResource(R.drawable.ic_home_48_px)
        tabImg2.setImageResource(R.drawable.ic_playlist_play_white_48_px)
        tabImg3.setImageResource(R.drawable.ic_favorite_white_48_px)
        tabImg4.setImageResource(R.drawable.ic_question_answer_white_48_px)
        tabImg5.setImageResource(R.drawable.ic_settings_white_48_px)









        // 탭 추가
        main_tab.addTab(main_tab.newTab()
                .setCustomView(tabView1))
        main_tab.addTab(main_tab.newTab()
                .setCustomView(tabView2))
        main_tab.addTab(main_tab.newTab()
                .setCustomView(tabView3))
        main_tab.addTab(main_tab.newTab()
                .setCustomView(tabView4))
        main_tab.addTab(main_tab.newTab()
                .setCustomView(tabView5))

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
//                        tabText1.setTextColor(R.color.now)
                        tabImg1.setImageResource(R.drawable.ic_home_white_48_px)
                        tab.setCustomView(tabView1)

                    }
                    1 -> {
//                        tabText2.setTextColor(R.color.now)
                        tabImg2.setImageResource(R.drawable.ic_playlist_play_white_48_px)
                        tab.setCustomView(tabView2)
                    }
                    2 -> {
//                        tabText3.setTextColor(R.color.now)
                        tabImg3.setImageResource(R.drawable.ic_favorite_white_48_px)
                        tab.setCustomView(tabView3)
                    }
                    3 -> {
//                        tabText4.setTextColor(R.color.now)
                        tabImg4.setImageResource(R.drawable.ic_question_answer_white_48_px)
                        tab.setCustomView(tabView4)
                    }
                    4 -> {
//                        tabText5.setTextColor(R.color.now)
                        tabImg5.setImageResource(R.drawable.ic_settings_white_48_px)
                        tab.setCustomView(tabView3)

                    }
                }

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                main_viewPager.currentItem = tab!!.position

                when (tab.position) {
                    0 -> {
//                        tabText1.setTextColor(R.color.changed)
                        tabImg1.setImageResource(R.drawable.ic_home_48_px)
                        tab.setCustomView(tabView1)
                    }
                    1 -> {
//                        tabText2.setTextColor(R.color.changed)
                        tabImg2.setImageResource(R.drawable.ic_playlist_play_48_px)
                        tab.setCustomView(tabView2)
                    }
                    2 -> {

//                        tabText3.setTextColor(R.color.changed)
                        tabImg3.setImageResource(R.drawable.ic_favorite_48_px)
                        tab.setCustomView(tabView3)
                    }
                    3 -> {

//                        tabText4.setTextColor(R.color.changed)
                        tabImg4.setImageResource(R.drawable.ic_question_answer_48_px)
                        tab.setCustomView(tabView4)
                        val intent = Intent(applicationContext, CommunityActivity::class.java)
                        startActivityForResult(intent, COMMUNITYACTIVITY)
                    }
                    4 -> {

//                        tabText5.setTextColor(R.color.changed)
                        tabImg5.setImageResource(R.drawable.ic_settings_48_px)
                        tab.setCustomView(tabView5)
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