package com.vplate

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 탭 추가
        main_tab.addTab(main_tab.newTab().setText("Tab1"))
        main_tab.addTab(main_tab.newTab().setText("Tab2"))
        main_tab.addTab(main_tab.newTab().setText("Home"))
        main_tab.addTab(main_tab.newTab().setText("Tab4"))
        main_tab.addTab(main_tab.newTab().setText("Tab5"))

        var tabAdapter = TabAdapter(supportFragmentManager, main_tab.tabCount)

        main_viewPager.adapter = tabAdapter

        main_viewPager.currentItem = 0
        main_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_tab))

        main_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                main_viewPager.currentItem = tab!!.position
                when(tab!!.position){
                    0 ->{

                    }
                    1->{

                    }
                    2->{

                    }
                    3->{

                    }
                    4->{

                    }
                }
            }
        })

    }

}