package com.vplate.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.vplate.R
import com.vplate.TabAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val COMMUNITYACTIVITY = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // sm: 탭 추가
        main_tab.addTab(main_tab.newTab().setText("Home"))
        main_tab.addTab(main_tab.newTab().setText("My video"))
        main_tab.addTab(main_tab.newTab().setText("Like"))
        main_tab.addTab(main_tab.newTab().setText("Community"))
        main_tab.addTab(main_tab.newTab().setText("My page"))

        var tabAdapter = TabAdapter(supportFragmentManager, main_tab.tabCount)

        main_viewPager.adapter = tabAdapter

        main_viewPager.currentItem = 0
        main_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_tab))

        main_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                main_viewPager.currentItem = tab!!.position
                when (tab.position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {
                        val intent = Intent(applicationContext, CommunityActivity::class.java)
                        startActivityForResult(intent, COMMUNITYACTIVITY)
                    }
                    4 -> {

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