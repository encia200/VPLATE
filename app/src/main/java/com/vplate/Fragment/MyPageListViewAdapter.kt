package com.vplate.Fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.vplate.R

/**
 * Created by chosoomin on 2018. 1. 6..
 */
class MyPageListViewAdapter : BaseAdapter {


    private var ITEM_VIEW_TYPE_MAX = 2
    private var context: Context? = null
    private var layout: Int? = null
    private var data : ArrayList<MyPageListViewItem> = ArrayList()
//    var viewHolder : viewholder? = null



    constructor(context :Context, layout : Int, data : ArrayList<MyPageListViewItem>){

        this.data = data
        this.layout = layout
        this.context = context
        Log.v("데이타",data.toString())
    }

//    private val listViewItemList = ArrayList<MyPageListViewItem>()


    override fun getCount(): Int {
        Log.v("getCount",data!!.size.toString())
        return data!!.size
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        Log.v("getView","성공!")
//        var viewHolder=viewholder()
        var pos : Int  = position
        var context : Context = parent.context
        var convertView : View? = null
        var inflater : LayoutInflater? = null
        if(convertView == null){
            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.item_mypage,parent,false)
            var a : String = data!!.get(pos).getTitle() as String
            Log.v("seo",a)

        }
        var text : TextView = convertView!!.findViewById(R.id.mypage_list_name) as TextView
        text.setText(data.get(position).getTitle())



        return convertView
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return data!![position]
    }

}
class viewholder{

    var text : TextView? = null

}