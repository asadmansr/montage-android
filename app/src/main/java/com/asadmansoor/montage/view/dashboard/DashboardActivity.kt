package com.asadmansoor.montage.view.dashboard

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asadmansoor.montage.R
import com.asadmansoor.montage.model.UserProperties
import com.asadmansoor.montage.adapter.DashboardAdapter
import com.asadmansoor.montage.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import com.asadmansoor.montage.db.entity.User
import com.asadmansoor.montage.view.userGeneration.GenerateUserActivity


private const val DEFAULT = 0
private const val USER_REQUEST = 1

class DashboardActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val userRecyclerView = findViewById<RecyclerView>(R.id.rv_dashboard_user)
        val dashboardAdapter = DashboardAdapter(this){ user: User, _: Int ->
            deleteUser(user)
        }
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = dashboardAdapter

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.allUsers.observe(this, Observer { users ->
            users?.let {
                Log.d("Montage", "Observer")
                dashboardAdapter.setUsers(it)
            }
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
                userViewModel.deleteAll()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(user: User){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.dialog_message)
        builder.setPositiveButton(R.string.dialog_positive){ _, _ ->
            userViewModel.delete(user)
        }
        builder.setNegativeButton(R.string.dialog_negative){ _, _ ->
        }
        val alert = builder.create()
        alert.setTitle(R.string.dialog_title)
        alert.show()
    }
}
