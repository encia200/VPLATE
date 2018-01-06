package com.vplate.Fragment

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.util.*

/**
 * Created by chosoomin on 2018. 1. 6..
 */
class MyPageListViewAdapter : BaseAdapter() {

    private var context: Context? = null

    private val listViewItemList = ArrayList<MyPageListViewItem>()
    fun setContext(context: Context) {
        this.context = context
    }

    override fun getCount(): Int {
        return listViewItemList.size
    }

    override fun getViewTypeCount(): Int {
        return ITEM_VIEW_TYPE_MAX
    }

    override fun getItemViewType(position: Int): Int {
        return listViewItemList[position].getType()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        //        final Context context = parent.getContext();
        val viewType = getItemViewType(position)


//        if (convertView == null) {
//            val inflater = context!!.getSystemService(context!!) as LayoutInflater
//
//            val mItem = listViewItemList[position]
//
//            when (viewType) {
//                ITEM_VIEW_TYPE_SECTION -> {
//                    convertView = inflater.inflate(R.layout.mypage_list_section, parent, false)
//
//                    val sectionTextView = convertView!!.findViewById(R.id.mypage_section) as TextView
//                    sectionTextView.setText(mItem.getSection())
//                }
//
//                ITEM_VIEW_TYPE_STRS -> {
//                    convertView = inflater.inflate(R.layout.mypage_list_item, parent, false)
//
//                    val itemTextView = convertView!!.findViewById(R.id.mypage_item) as TextView
//                    itemTextView.setText(mItem.getItem())
//                }
//
//                ITEM_VIEW_TYPE_NEXT -> {
//                    convertView = inflater.inflate(R.layout.mypage_list_next, parent, false)
//
//                    val titleTextView = convertView!!.findViewById(R.id.mypage_title) as TextView
//                    titleTextView.setText(mItem.getTitle())
//                }
//
//                ITEM_VIEW_TYPE_TOGG -> {
//                    convertView = inflater.inflate(R.layout.mypage_list_toggle, parent, false)
//
//                    val toggleTextView = convertView!!.findViewById(R.id.mypage_tittle_t) as TextView
//                    toggleTextView.setText(mItem.getTitle_t())
//                }
//            }
//
//
//        }

        return convertView
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return listViewItemList[position]
    }

    fun addItem(str: String, temp: Int) {
        val item = MyPageListViewItem()

        if (temp == 0) {
            item.setType(ITEM_VIEW_TYPE_SECTION)
            item.setSection(str)
        }
        else if (temp == 1) {
            item.setType(ITEM_VIEW_TYPE_STRS)
            item.setItem(str)
        }
        else if (temp == 2) {
            item.setType(ITEM_VIEW_TYPE_TOGG)
            item.setTitle_toggle(str)
        }
        else {
            item.setType(ITEM_VIEW_TYPE_NEXT)
            item.setTitle(str)
        }

        listViewItemList.add(item)
    }

    companion object {
        private val ITEM_VIEW_TYPE_SECTION = 0
        private val ITEM_VIEW_TYPE_STRS = 1
        private val ITEM_VIEW_TYPE_NEXT = 2
        private val ITEM_VIEW_TYPE_TOGG = 3
        private val ITEM_VIEW_TYPE_MAX = 4
    }
}