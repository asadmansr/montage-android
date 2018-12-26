package com.asadmansoor.montage.ui

import android.app.Activity
import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.asadmansoor.montage.R
import com.asadmansoor.montage.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import com.asadmansoor.montage.db.entity.User


private const val DEFAULT = 0
private const val USER_REQUEST = 1

class DashboardActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        rv_dashboard_user.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        var users = ArrayList<User>()
        val adapter = UserAdapter(users) { userItem: User, userPos: Int ->
            Log.d("userList", userPos.toString())
            deleteUser(userItem)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == USER_REQUEST && resultCode == Activity.RESULT_OK){
            val userName = data?.getStringExtra(GenerateUserActivity.EXTRA_NAME) as String
            val imgIndex = data?.getIntExtra(GenerateUserActivity.EXTRA_IMG_RES, DEFAULT)
            val colorIndex = data?.getIntExtra(GenerateUserActivity.EXTRA_COLOR_RES, DEFAULT)

            Log.d("userInfo", userName)
            Log.d("userInfo", imgIndex.toString())
            Log.d("userInfo", colorIndex.toString())

            val user = User(name = userName, imgRes = imgIndex, colorRes = colorIndex)
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
            builder.setMessage("Delete user?")
            builder.setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, id ->
                    userViewModel.delete(user)
                })
            builder.setNegativeButton("CANCEL",
                DialogInterface.OnClickListener { dialog, id ->

                })
            builder.create()
        }
        alertDialog?.show()
    }
}
