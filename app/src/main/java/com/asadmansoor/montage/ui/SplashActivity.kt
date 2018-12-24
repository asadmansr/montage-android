package com.asadmansoor.montage.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

private const val SPLASH_DELAY: Long = 2000
private const val USER_INDEX: Int = 14

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupView()
    }

    private fun setupView(){
        Thread(Runnable {
            generateRandomViewUser()
            Thread.sleep(SPLASH_DELAY)
            startDashboardActivity()
        }).start()
    }

    private fun generateRandomViewUser(){
        val randomIndex = Random().nextInt(USER_INDEX)
        iv_splash_user.setImageResource(UserProperties.imgResource[randomIndex])
    }

    private fun startDashboardActivity(){
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}
