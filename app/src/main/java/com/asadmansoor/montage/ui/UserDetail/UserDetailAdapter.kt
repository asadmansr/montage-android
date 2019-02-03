package com.asadmansoor.montage.ui.UserDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.asadmansoor.montage.R

class UserDetailAdapter (var context: Context, var titleList: ArrayList<String>, val contentList: ArrayList<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return titleList.size
    }

    override fun getItem(position: Int): Any {
        return titleList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.layout_list_detail, parent, false)
        val detailTitle = rowView.findViewById(R.id.tv_list_title) as TextView
        val detailContent = rowView.findViewById(R.id.tv_list_content) as TextView

        detailTitle.text = titleList[position]
        detailContent.text = contentList[position]

        return rowView
    }
}