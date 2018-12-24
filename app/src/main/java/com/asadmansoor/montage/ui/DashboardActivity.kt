package com.asadmansoor.montage.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.asadmansoor.montage.R
import com.asadmansoor.montage.db.entity.User
import com.asadmansoor.montage.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        rv_dashboard_user.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        //var users = ArrayList<UserTemp>()
        var users = ArrayList<User>()
        val adapter = UserAdapter(users)
        rv_dashboard_user.adapter = adapter

        //val userViewModel = UserViewModel(application)
        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.getUserList().observe(this, Observer { mUsers: List<User>? ->
            users = mUsers as ArrayList<User>
            adapter.setUsers(users)
        })


        fab_dashboard_add.setOnClickListener {
            startGenerateUserActivity()
        }
    }

    private fun startGenerateUserActivity(){
        val intent = Intent(this, GenerateUserActivity::class.java)
        startActivity(intent)
    }
}
