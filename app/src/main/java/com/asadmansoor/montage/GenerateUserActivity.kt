package com.asadmansoor.montage

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_generate_user.*

class GenerateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_user)

        next_btn.setOnClickListener {
            val intent = Intent(this, UserContactActivity::class.java)
            startActivity(intent)
        }

        back_btn.setOnClickListener {
            finish()
        }
    }
}
