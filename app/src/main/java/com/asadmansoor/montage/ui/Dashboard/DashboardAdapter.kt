package com.asadmansoor.montage.ui.Dashboard

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import com.asadmansoor.montage.db.entity.User


class DashboardAdapter (var userList: ArrayList<User>, val clickListener: (User, Int) -> Unit) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.layout_list_user, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user: User = userList[p1]

        p0.userName.text = user.name
        p0.userImage.setImageResource(UserProperties.imgResource[user.imgRes])

        val userImageRef = p0.userImage.background as GradientDrawable
        userImageRef.setColor(Color.parseColor(UserProperties.colorResource[user.colorRes]))

        p0.viewContainer.setOnClickListener { clickListener(user, p1) }
    }

    fun setUsers(users: List<User>){
        userList = users as ArrayList<User>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val viewContainer = itemView.findViewById(R.id.rl_container) as RelativeLayout
        val userImage = itemView.findViewById(R.id.iv_recycler_user) as ImageView
        val userName = itemView.findViewById(R.id.tv_recycler_name) as TextView
    }
}