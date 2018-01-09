package com.vplate.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.vplate.R
import com.vplate.activity.NickChangeAcitivity
import com.vplate.activity.PwChangeActivity

class MyPageFragment : Fragment(),AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.v("kok",position.toString())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_my_page, container, false)
        var listView = v.findViewById(R.id.mypage_list) as ListView
        var data : ArrayList<MyPageListViewItem> =  ArrayList()
        var aa : MyPageListViewItem = MyPageListViewItem("닉네임 변경")
        var bb : MyPageListViewItem = MyPageListViewItem("비밀번호 변경")
        data.add(aa)
        data.add(bb)
//        data.add(cc)
//        data.add(dd)
        var mAdapter : MyPageListViewAdapter = MyPageListViewAdapter(activity,R.layout.item_mypage,data)
        listView.adapter = mAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0 -> {
                    val intent = Intent(context, NickChangeAcitivity::class.java)
                    startActivity(intent)
                }
                1->{
                    val intent1 = Intent(context, PwChangeActivity::class.java)
                    startActivity(intent1)

                }


            }
        }




        var listView1 = v.findViewById(R.id.support_list) as ListView
        var data1 : ArrayList<MyPageListViewItem> =  ArrayList()
        var aa1 : MyPageListViewItem = MyPageListViewItem("공지사항")
        var bb1 : MyPageListViewItem = MyPageListViewItem("FAQ")
        var cc1 : MyPageListViewItem = MyPageListViewItem("로그아웃")

        data1.add(aa1)
        data1.add(bb1)
        data1.add(cc1)
        var mAdapter1 : MyPageListViewAdapter = MyPageListViewAdapter(activity,R.layout.item_mypage,data)
        listView1.adapter = mAdapter1

        listView1.setOnItemClickListener { parent, view, position, id ->

            when(position){
                0 -> {

                    Toast.makeText(activity,"공지사항",Toast.LENGTH_LONG).show()
//                    val intent = Intent(context, NickChangeAcitivity::class.java)
//                    startActivity(intent)
                }
                1->{

                    Toast.makeText(activity,"FAQ",Toast.LENGTH_LONG).show()
//                    val intent = Intent(context, TemplateInfoActivity::class.java)
//                    startActivity(intent)
                }
                2->{
                    Toast.makeText(activity,"로그아웃",Toast.LENGTH_LONG).show()
                }
            }


        }











   if(arguments != null){

        }
        return v
    }


}