package com.asadmansoor.montage.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import kotlinx.android.synthetic.main.activity_user_information.*


private const val DEFAULT_INDEX = 0


class UserInformationActivity : AppCompatActivity() {

    private var listView : ListView? = null
    private val labelList = arrayListOf<String>("Username","Password","Phone","City","State","Timezone")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        val intent = intent
        val userName = intent.getStringExtra(UserProperties.EXTRA_NAME)
        val imgIndex = intent.getIntExtra(UserProperties.EXTRA_IMG_RES, DEFAULT_INDEX)
        val colorIndex = intent.getIntExtra(UserProperties.EXTRA_COLOR_RES, DEFAULT_INDEX)
        setupUserHeader(userName, imgIndex)

        listView = findViewById(R.id.lv_information_list)
        val adapter = InformationAdapter(this, labelList)
        listView!!.adapter = adapter

        btn_information_next.setOnClickListener {
            val dashboardIntent = Intent()
            dashboardIntent.putExtra(UserProperties.EXTRA_NAME, userName)
            dashboardIntent.putExtra(UserProperties.EXTRA_IMG_RES, imgIndex)
            dashboardIntent.putExtra(UserProperties.EXTRA_COLOR_RES, colorIndex)
            setResult(Activity.RESULT_OK, dashboardIntent)
            finish()
        }

        btn_information_back.setOnClickListener {
            val generateUserIntent = Intent(this, GenerateUserActivity::class.java)
            generateUserIntent.putExtra(UserProperties.EXTRA_NAME, userName)
            generateUserIntent.putExtra(UserProperties.EXTRA_IMG_RES, imgIndex)
            generateUserIntent.putExtra(UserProperties.EXTRA_COLOR_RES, colorIndex)
            generateUserIntent.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
            startActivity(generateUserIntent)
            finish()
        }
    }


    private fun setupUserHeader(userName: String, imgIndex: Int){
        iv_splash_user.setImageResource(UserProperties.imgResource[imgIndex])
        tv_information_name.text = userName
    }
}
