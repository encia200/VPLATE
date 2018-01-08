package com.vplate.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.vplate.R

class MyPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_my_page, container, false)
        var listView = v.findViewById(R.id.mypage_list) as ListView
        var data : ArrayList<MyPageListViewItem> =  ArrayList()
        var aa : MyPageListViewItem = MyPageListViewItem("닉네임 변경")
        var bb : MyPageListViewItem = MyPageListViewItem("비밀번호 변경")
        var cc : MyPageListViewItem = MyPageListViewItem("로그아웃")
        var dd : MyPageListViewItem = MyPageListViewItem("title")

        data.add(aa)
        data.add(bb)
        data.add(cc)
        data.add(dd)
        var mAdapter : MyPageListViewAdapter = MyPageListViewAdapter(activity,R.layout.item_mypage,data)
        listView.adapter = mAdapter


        var listView1 = v.findViewById(R.id.support_list) as ListView
        var data1 : ArrayList<MyPageListViewItem> =  ArrayList()
        var aa1 : MyPageListViewItem = MyPageListViewItem("닉네임 변경")
        var bb1 : MyPageListViewItem = MyPageListViewItem("비밀번호 변경")
        var cc1 : MyPageListViewItem = MyPageListViewItem("로그아웃")

        data1.add(aa1)
        data1.add(bb1)
        data1.add(cc1)
        var mAdapter1 : MyPageListViewAdapter = MyPageListViewAdapter(activity,R.layout.item_mypage,data)
        listView1.adapter = mAdapter1












   if(arguments != null){

        }
        return v
    }


}