package com.asadmansoor.montage.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.asadmansoor.montage.R
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {

    val titleList = arrayListOf<String>("Name","Email","Username","Password","Phone","City","State","Timezone")
    val contentList = arrayListOf<String>("John Smith","john@example.com","j.smith","pass1234","123-456-9999","Toronto","ON","-5:00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailAdapter(this, titleList, contentList)
        lv_user_details.adapter = adapter
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
