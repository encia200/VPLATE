package com.vplate.Fragment

import android.util.Log

/**
 * Created by chosoomin on 2018. 1. 6..
 */
class MyPageListViewItem() {

    private var title: String? = null



    constructor(title : String) : this(){
        this.title = title
        Log.v("item생성자","성공")
    }



    fun getTitle(): String? {
        return title
    }


    fun MyPageListViewItem(title :String){
        this.title = title
    }


}