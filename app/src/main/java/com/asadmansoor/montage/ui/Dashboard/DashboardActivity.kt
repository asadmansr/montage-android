package com.asadmansoor.montage.ui.Dashboard

import android.app.Activity
import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import com.asadmansoor.montage.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import com.asadmansoor.montage.db.entity.User
import com.asadmansoor.montage.ui.UserGeneration.GenerateUserActivity
import com.asadmansoor.montage.ui.UserDetail.UserDetailActivity


private const val DEFAULT = 0
private const val USER_REQUEST = 1

class DashboardActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        rv_dashboard_user.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        var users = ArrayList<User>()
        val adapter = DashboardAdapter(users) { userItem: User, userPos: Int ->
            startUserDetailActivity(userItem)
        }
        rv_dashboard_user.adapter = adapter


        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel?.getUserList().observe(this, Observer { mUsers: List<User>? ->
            users = mUsers as ArrayList<User>
            adapter.setUsers(users)
        })


        fab_dashboard_add.setOnClickListener {
            startGenerateUserActivity()
        }
    }


    private fun startGenerateUserActivity(){
        val intent = Intent(this, GenerateUserActivity::class.java)
        startActivityForResult(intent, USER_REQUEST)
    }


    private fun startUserDetailActivity(userItem: User){
        val arrExtra = generateUserArray(userItem)
        val imgExtra = userItem.imgRes
        val colorExtra = userItem.colorRes

        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(UserProperties.EXTRA_USER_ARR, arrExtra)
        intent.putExtra(UserProperties.EXTRA_IMG_RES, imgExtra)
        intent.putExtra(UserProperties.EXTRA_COLOR_RES, colorExtra)
        startActivity(intent)
    }


    private fun generateUserArray(userItem: User): ArrayList<String>{
        val userArr = ArrayList<String>()
        userArr.add(userItem.name)
        userArr.add(userItem.email)
        userArr.add(userItem.username)
        userArr.add(userItem.password)
        userArr.add(userItem.phone)
        userArr.add(userItem.city)
        userArr.add(userItem.state)
        userArr.add(userItem.timezone)
        return userArr
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == USER_REQUEST && resultCode == Activity.RESULT_OK){
            val name = data?.getStringExtra(UserProperties.EXTRA_NAME) as String
            val email = data.getStringExtra(UserProperties.EXTRA_EMAIL) as String
            val imgIndex = data.getIntExtra(UserProperties.EXTRA_IMG_RES, DEFAULT)
            val colorIndex = data.getIntExtra(UserProperties.EXTRA_COLOR_RES, DEFAULT)

            val username = data.getStringExtra(UserProperties.EXTRA_USERNAME) as String
            val password = data.getStringExtra(UserProperties.EXTRA_PASSWORD) as String
            val phone = data.getStringExtra(UserProperties.EXTRA_PHONE) as String
            val city = data.getStringExtra(UserProperties.EXTRA_CITY) as String
            val state = data.getStringExtra(UserProperties.EXTRA_STATE) as String
            val timezone = data.getStringExtra(UserProperties.EXTRA_TIMEZONE) as String

            val user = User(name = name, email = email, imgRes = imgIndex, colorRes = colorIndex,
                username = username, password = password, phone = phone, city = city, state = state, timezone = timezone)
            userViewModel.insert(user)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete_all -> {
                userViewModel.deleteAllUsers()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun deleteUser(user: User){
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.dialog_message)
            builder.setPositiveButton(R.string.dialog_positive,
                DialogInterface.OnClickListener { dialog, id ->
                    userViewModel.delete(user)
                })
            builder.setNegativeButton(R.string.dialog_negative,
                DialogInterface.OnClickListener { dialog, id ->

                })
            builder.create()
        }
        alertDialog?.show()
    }
}
