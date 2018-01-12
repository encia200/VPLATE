package com.vplate.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.vplate.Network.CommonData
import com.vplate.R
import com.vplate.activity.LoginMainActivity
import com.vplate.activity.PwChangeActivity
import kotlinx.android.synthetic.main.fragment_my_page.view.*

class MyPageFragment : Fragment(),AdapterView.OnItemClickListener {
    var settings : SharedPreferences? = null
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.v("kok",position.toString())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_my_page, container, false)
        settings = this.activity.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        v!!.mypage_name.setText(CommonData.loginResponse!!.data.name)
        v!!.mypage_email.setText(CommonData.loginResponse!!.data.email)
        v!!.mypage_nickname.setText(CommonData.loginResponse!!.data.nickname)

//        var ad = URL(CommonData.loginResponse!!.data.profile) as URL
//        var connection = ad.openConnection()
//        connection.connect()
//        var bis = BufferedInputStream(connection.getInputStream())
//        var bm = BitmapFactory.decodeStream(bis)
//        bis.close()
//        v!!.mypage_profile.setImageBitmap(bm)
//        v!!.mypage_profile.setImageResource(R.drawable.icon_person)
//        var b = CommonData.loginResponse!!.data.profile+".jpg"
////        var ad = URL(b) as URL
//        var gg = Uri.parse(b)
//        v!!.mypage_profile.setImageURI(gg)
        Glide.with(this)
                .load(CommonData.loginResponse!!.data.profile)
                .into(v.mypage_profile)




        var listView = v.findViewById(R.id.mypage_list) as ListView
        var data : ArrayList<MyPageListViewItem> =  ArrayList()
        var aa : MyPageListViewItem = MyPageListViewItem("닉네임 변경")
        var bb : MyPageListViewItem = MyPageListViewItem("비밀번호 변경")
        data.add(aa)
        data.add(bb)

        var mAdapter : MyPageListViewAdapter = MyPageListViewAdapter(activity,R.layout.item_mypage,data)
        listView.adapter = mAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0 -> {
                    Toast.makeText(activity,"서비스 준비중입니다.",Toast.LENGTH_LONG).show()
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
        var mAdapter1 : MyPageListViewAdapter = MyPageListViewAdapter(activity,R.layout.item_mypage,data1)
        listView1.adapter = mAdapter1

        listView1.setOnItemClickListener { parent, view, position, id ->

            when(position){
                0 -> {
                    Toast.makeText(activity,"서비스 준비중입니다.",Toast.LENGTH_LONG).show()
                }
                1->{

                    Toast.makeText(activity,"서비스 준비중입니다.",Toast.LENGTH_LONG).show()
                }
                2->{
                    val intent = Intent(context, LoginMainActivity::class.java)

                    val editor = settings!!.edit()
                    editor.clear()
                    editor.commit()
                    startActivity(intent)
                }
            }

        }



   if(arguments != null){

        }
        return v
    }


}