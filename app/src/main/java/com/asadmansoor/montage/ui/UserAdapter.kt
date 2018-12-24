package com.asadmansoor.montage.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import com.asadmansoor.montage.db.entity.User
import java.util.Random


class UserAdapter(var userList: ArrayList<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
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

        val randomIndex = Random().nextInt(14)
        p0.userImage.setImageResource(UserProperties.imgResource[randomIndex])


        val index = Random().nextInt(4)


        val temp = p0.userImage.background as GradientDrawable
        temp.setColor(Color.parseColor(UserProperties.colorResource[index]))

        //val temp = p0.userLayout.background as GradientDrawable
        //temp.setColor(Color.RED)

        val temp2 = p0.userLayout.background as GradientDrawable
        temp2.setStroke( 28,Color.parseColor(UserProperties.colorResource[index]))
    }

    fun setUsers(users: List<User>){
        userList = users as ArrayList<User>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //val userLayout = itemView.findViewById(R.id.rl_recycler_user) as RelativeLayout
        val userLayout = itemView.findViewById(R.id.vw_recycler_user) as View
        val userImage = itemView.findViewById(R.id.iv_recycler_user) as ImageView
        val userName = itemView.findViewById(R.id.tv_recycler_name) as TextView
    }
}