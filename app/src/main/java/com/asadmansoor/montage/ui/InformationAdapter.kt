package com.asadmansoor.montage.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.asadmansoor.montage.R

class InformationAdapter (var context: Context, var infoList: ArrayList<String>) : BaseAdapter() {

    private var mObjects : ArrayList<String> = ArrayList<String>()
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init {
        mObjects = infoList
    }

    override fun getCount(): Int {
        return infoList.size
    }

    override fun getItem(position: Int): Any {
        return infoList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.layout_list_information, parent, false)
        val infoTitle = rowView.findViewById(R.id.tv_generate_name) as TextView
        val infoField = rowView.findViewById(R.id.et_user_name) as EditText
        val infoRandom = rowView.findViewById(R.id.btn_generate_name) as Button

        infoTitle.text = infoList[position]

        return rowView
    }
}