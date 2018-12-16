package com.asadmansoor.montage

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_information.*

class UserInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        next_btn.setOnClickListener {
            val intent = Intent(this, UserStoryActivity::class.java)
            startActivity(intent)
        }

        back_btn.setOnClickListener {
            finish()
        }
    }
}
