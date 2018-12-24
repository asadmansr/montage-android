package com.asadmansoor.montage.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.asadmansoor.montage.R
import kotlinx.android.synthetic.main.activity_user_story.*

class UserStoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_story)

        next_btn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        back_btn.setOnClickListener {
            finish()
        }
    }
}
