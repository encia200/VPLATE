package com.vplate.activity

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.vplate.R
import com.vplate.community.adapter.fragments.VideoRecyclerViewFragment

class CommunityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        setContentView(R.layout.activity_community)

        if (savedInstanceState == null) {
            addRecyclerView()
        }

        val constraintLayout = findViewById(R.id.fragment_container) as LinearLayout
        val animationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

    private fun addRecyclerView() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, VideoRecyclerViewFragment())
                .commit()
    }

    override fun finish() {
        super.finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        setResult(Activity.RESULT_OK)
    }
}