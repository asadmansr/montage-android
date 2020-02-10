package com.asadmansoor.montage.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asadmansoor.montage.R
import com.asadmansoor.montage.model.AppConstants
import com.asadmansoor.montage.model.UserProperties
import com.asadmansoor.montage.view.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setUserView()
        startTimer()
    }

    private fun setUserView() {
        iv_splash_user.setImageResource(generateRandomUser())
    }

    private fun startTimer() {
        Thread(Runnable {
            Thread.sleep(AppConstants.SPLASH_DELAY)
            startDashboardActivity()
        }).start()
    }

    private fun generateRandomUser(): Int {
        val randomIndex = Random().nextInt(AppConstants.USER_INDEX)
        return UserProperties.imgResource[randomIndex]
    }

    private fun startDashboardActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}
