package com.vplate.Myvideo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vplate.R

/**
 * Created by chosoomin on 2018. 1. 1..
 */
class CompleteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_fifth, container, false)

        if(arguments != null){

        }
        return v
    }
}