package com.asadmansoor.montage.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asadmansoor.montage.R
import com.asadmansoor.montage.db.entity.User
import com.asadmansoor.montage.model.UserProperties


class DashboardAdapter internal constructor(
    context: Context,
    val clickListener: (User, Int) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.UserViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<User>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewContainer: RelativeLayout = itemView.findViewById(R.id.rl_container)
        val userImage: ImageView = itemView.findViewById(R.id.iv_recycler_user)
        val userName: TextView = itemView.findViewById(R.id.tv_recycler_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.layout_list_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = users[position]

        holder.userName.text = currentUser.name
        holder.userImage.setImageResource(UserProperties.imgResource[currentUser.imgRes])

        val userImageRef = holder.userImage.background as GradientDrawable
        userImageRef.setColor(Color.parseColor(UserProperties.colorResource[currentUser.colorRes]))

        holder.viewContainer.setOnClickListener {
            clickListener(currentUser, position)
        }
    }

    internal fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size
}