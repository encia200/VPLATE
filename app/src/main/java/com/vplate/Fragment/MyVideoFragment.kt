package com.vplate.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vplate.Myvideo.fragments.CompleteFragment
import com.vplate.Myvideo.fragments.ProgressingFragment
import com.vplate.R
import kotlinx.android.synthetic.main.fragment_myvideo.view.*


class MyVideoFragment : Fragment(), View.OnClickListener {

    private var vi : View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vi = inflater!!.inflate(R.layout.fragment_myvideo, container, false)

        vi!!.myvideo_progressing_videos.setOnClickListener(this)
        vi!!.myvideo_complete_videos.setOnClickListener(this)

        val fragment = ProgressingFragment()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.myvideo_fragment, fragment)
        fragmentTransaction.commit()

        return vi
    }

    override fun onClick(v: View?) {
        if (view == null) return
        var fragment: Fragment? = null

        when (v!!.id) {
            R.id.myvideo_progressing_videos -> { // 미완성된 영상 버튼 눌렀을 경우
                vi!!.myvideo_progressing_videos.setImageResource(R.drawable.line_notcom)
                vi!!.myvideo_complete_videos.setImageResource(R.drawable.complete)

                fragment = ProgressingFragment()
            }
            R.id.myvideo_complete_videos -> { // 완성된 영상 버튼 눌렀을 경우
                vi!!.myvideo_progressing_videos.setImageResource(R.drawable.notcom)
                vi!!.myvideo_complete_videos.setImageResource(R.drawable.line_complete)

                fragment = CompleteFragment()
            }
        }
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myvideo_fragment, fragment)
        fragmentTransaction.commit()
    }
}