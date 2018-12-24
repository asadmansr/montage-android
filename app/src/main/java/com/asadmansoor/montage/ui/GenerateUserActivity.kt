package com.asadmansoor.montage.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.asadmansoor.montage.R
import kotlinx.android.synthetic.main.activity_generate_user.*

class GenerateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_user)

        btn_generate_save.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        btn_generate_close.setOnClickListener {
            finish()
        }
    }
}
