package com.asadmansoor.montage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.asadmansoor.montage.R
import com.asadmansoor.montage.view.UserGeneration.UserInformationActivity

class GenerateInfoAdapter (var context: Context, var infoList: ArrayList<String>, val dataList: ArrayList<String>) : BaseAdapter() {

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
        infoField.setText(dataList[position])

        if (position > 3) {
            infoRandom.visibility = View.GONE
        }

        infoRandom.setOnClickListener {
            when (position) {
                0 -> (context as UserInformationActivity).randomizeUsername()
                1 -> (context as UserInformationActivity).randomizePassword()
                2 -> (context as UserInformationActivity).randomizePhone()
                3 -> (context as UserInformationActivity).randomizeLocation()
            }
        }

        return rowView
    }
}