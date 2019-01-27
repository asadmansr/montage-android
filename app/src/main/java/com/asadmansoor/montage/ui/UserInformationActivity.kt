package com.asadmansoor.montage.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import com.asadmansoor.montage.api.APIManager
import com.asadmansoor.montage.model.UserInfoAPI
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import kotlinx.android.synthetic.main.activity_user_information.*


private const val DEFAULT_INDEX = 0
private const val BASE_URL = "https://randomuser.me/api/"
private const val PARAMS_NAME = "?inc=login,phone,location"


class UserInformationActivity : AppCompatActivity() {

    private var listView : ListView? = null
    private var adapter : InformationAdapter? = null
    private val labelList = arrayListOf<String>("Username","Password","Phone","City","State","Timezone")
    private var infoList = arrayListOf("","","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        val intent = intent
        val userName = intent.getStringExtra(UserProperties.EXTRA_NAME)
        val userEmail = intent.getStringExtra(UserProperties.EXTRA_EMAIL)
        val imgIndex = intent.getIntExtra(UserProperties.EXTRA_IMG_RES, DEFAULT_INDEX)
        val colorIndex = intent.getIntExtra(UserProperties.EXTRA_COLOR_RES, DEFAULT_INDEX)
        setupUserHeader(userName, imgIndex)

        listView = findViewById<ListView>(R.id.lv_information_list)
        adapter = InformationAdapter(this, labelList, infoList)
        listView!!.adapter = adapter

        getUserInformation()

        btn_information_next.setOnClickListener {
            showDashboardScreen(userName, userEmail, imgIndex, colorIndex)
        }

        btn_information_back.setOnClickListener {
            showGenerateUserScreen(userName, userEmail, imgIndex, colorIndex)
        }
    }


    private fun getUserInformation(){
        (BASE_URL + PARAMS_NAME)
        .httpGet()
        .responseString { _, _, result ->
            result.failure {

            }
            result.success {
                val data = result.get()
                val userInfoDate = APIManager().parseUserInformation(data)
                populateInfoData(userInfoDate)
            }
        }
    }


    private fun populateInfoData(infoAPI: UserInfoAPI){
        infoList[0] = infoAPI.username
        infoList[1] = infoAPI.password
        infoList[2] = infoAPI.phone
        infoList[3] = infoAPI.city
        infoList[4] = infoAPI.state
        infoList[5] = infoAPI.timezone
        adapter!!.notifyDataSetChanged()
    }


    private fun setupUserHeader(userName: String, imgIndex: Int){
        iv_splash_user.setImageResource(UserProperties.imgResource[imgIndex])
        tv_information_name.text = userName
    }


    private fun showGenerateUserScreen(userName: String, userEmail: String, imgIndex: Int, colorIndex: Int){
        val generateUserIntent = Intent(this, GenerateUserActivity::class.java)
        generateUserIntent.putExtra(UserProperties.EXTRA_NAME, userName)
        generateUserIntent.putExtra(UserProperties.EXTRA_EMAIL, userEmail)
        generateUserIntent.putExtra(UserProperties.EXTRA_IMG_RES, imgIndex)
        generateUserIntent.putExtra(UserProperties.EXTRA_COLOR_RES, colorIndex)
        generateUserIntent.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        startActivity(generateUserIntent)
        finish()
    }


    private fun showDashboardScreen(userName: String, userEmail: String, imgIndex: Int, colorIndex: Int){
        val dashboardIntent = Intent()
        dashboardIntent.putExtra(UserProperties.EXTRA_NAME, userName)
        dashboardIntent.putExtra(UserProperties.EXTRA_EMAIL, userEmail)
        dashboardIntent.putExtra(UserProperties.EXTRA_IMG_RES, imgIndex)
        dashboardIntent.putExtra(UserProperties.EXTRA_COLOR_RES, colorIndex)
        setResult(Activity.RESULT_OK, dashboardIntent)
        finish()
    }
}
