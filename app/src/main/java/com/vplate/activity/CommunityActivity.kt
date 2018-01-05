package com.vplate.activity

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.vplate.R
import com.vplate.community.adapter.fragments.VideoRecyclerViewFragment

class CommunityActivity : AppCompatActivity() {
    companion object {
        lateinit var dialogData: DialogData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        setContentView(R.layout.activity_community)

        dialogData = DialogData()
        dialogData.setDialogData(false)
        dialogData.setListener(object : DialogData.ChangeListener {
            override fun onChange() {
                finish()
            }
        })

        if (savedInstanceState == null) {
            addRecyclerView()
        }

        val linearLayout_container = findViewById(R.id.fragment_container) as LinearLayout
        val animationDrawable = linearLayout_container.background as AnimationDrawable
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

    class DialogData {
        var data: Boolean? = null
        private var listener: ChangeListener? = null

        fun setListener(listener: ChangeListener) {
            this.listener = listener
        }

        fun setDialogData(data: Boolean) {
            this.data = data
            if (listener != null) listener!!.onChange()
        }

        interface ChangeListener {
            fun onChange()
        }
    }
}