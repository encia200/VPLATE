package com.vplate.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vplate.Myvideo.fragments.CompleteFragment
import com.vplate.Myvideo.fragments.ProgressingFragment
import com.vplate.R


class MyVideoFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        if (view == null) return
        var fragment: Fragment? = null
        when (v!!.id) {
            R.id.myvideo_complete_videos -> {
                fragment = CompleteFragment()
            }
            R.id.myvideo_progressing_videos -> {
                fragment = ProgressingFragment()
            }

        }
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myvideo_fragment, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_myvideo, container, false)

        val complete_videos: TextView = v.findViewById(R.id.myvideo_complete_videos) as TextView
        val progressing_videos: TextView = v.findViewById(R.id.myvideo_progressing_videos) as TextView
        complete_videos.setOnClickListener(this)
        progressing_videos.setOnClickListener(this)

        val fragment = ProgressingFragment()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.myvideo_fragment, fragment)
        fragmentTransaction.commit()

        return v
    }
}