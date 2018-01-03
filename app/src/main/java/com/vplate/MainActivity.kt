package com.vplate

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.vplate.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // sm: 탭 추가
        mainTab.addTab(mainTab.newTab().setText("Tab1"))
        mainTab.addTab(mainTab.newTab().setText("Tab2"))
        mainTab.addTab(mainTab.newTab().setText("Home"))
        mainTab.addTab(mainTab.newTab().setText("Tab4"))
        mainTab.addTab(mainTab.newTab().setText("Tab5"))

        var tabAdapter = TabAdapter(supportFragmentManager, mainTab.tabCount)

        mainViewPager.adapter = tabAdapter

        mainViewPager.currentItem = 0
        mainViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mainTab))

        mainTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                mainViewPager.currentItem = tab!!.position
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