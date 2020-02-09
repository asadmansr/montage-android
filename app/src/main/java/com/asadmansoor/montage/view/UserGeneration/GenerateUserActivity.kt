package com.asadmansoor.montage.view.UserGeneration

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import com.asadmansoor.montage.api.APIManager
import com.asadmansoor.montage.helper.UserHelper
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import kotlinx.android.synthetic.main.activity_generate_user.*
import java.util.*

private const val IMG_BOUND = 14
private const val COLOR_BOUND = 4

private const val BASE_URL = "https://randomuser.me/api/"
private const val PARAMS_NAME = "?inc=gender,email,name,nat&nat=au,gb,us,ca,nz"
private const val DEFAULT_INDEX = 0

class GenerateUserActivity : AppCompatActivity() {

    private var userName: String? = null
    private var userEmail: String? = null
    private var imgIndex: Int? = null
    private var colorIndex: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_user)
        setUserProfile()

        btn_generate_img.setOnClickListener {
            generateUserImg()
            generateColorIndex()
        }

        btn_generate_name.setOnClickListener {
            generateUserName()
        }

        btn_generate_save.setOnClickListener {
            saveNewUser()
        }

        btn_generate_close.setOnClickListener {
            cancelGeneration()
        }
    }


    private fun setUserProfile(){
        val intent = intent
        userName = intent.getStringExtra(UserProperties.EXTRA_NAME)
        userEmail = intent.getStringExtra(UserProperties.EXTRA_EMAIL)
        imgIndex = intent.getIntExtra(UserProperties.EXTRA_IMG_RES, DEFAULT_INDEX)
        colorIndex = intent.getIntExtra(UserProperties.EXTRA_COLOR_RES, DEFAULT_INDEX)

        if (userName == null){
            generateUserName()
            generateUserImg()
            generateColorIndex()
        } else {
            iv_user_image.setImageResource(UserProperties.imgResource[imgIndex!!])
            setNameField(userName!!)
            setEmailField(userEmail!!)
        }
    }


    private fun generateUserImg(){
        imgIndex = generateRandomIndex(IMG_BOUND)
        val randomIndex = imgIndex as Int
        iv_user_image.setImageResource(UserProperties.imgResource[randomIndex])

        val userImageRef = picture_rl.background as GradientDrawable
        userImageRef.setColor(Color.parseColor(UserProperties.colorPrimary))
    }


    private fun generateColorIndex(){
        colorIndex = generateRandomIndex(COLOR_BOUND)
    }


    private fun generateRandomIndex(bound: Int) : Int {
        return Random().nextInt(bound)
    }


    private fun saveNewUser(){
        userName = et_user_name.text.toString()
        userEmail = et_user_email.text.toString()

        val intent = Intent(this, UserInformationActivity::class.java)
        intent.putExtra(UserProperties.EXTRA_NAME, userName)
        intent.putExtra(UserProperties.EXTRA_EMAIL, userEmail)
        intent.putExtra(UserProperties.EXTRA_IMG_RES, imgIndex)
        intent.putExtra(UserProperties.EXTRA_COLOR_RES, colorIndex)

        intent.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        startActivity(intent)
        finish()
    }


    private fun cancelGeneration(){
        val intent = Intent()
        setResult(Activity.RESULT_CANCELED, intent)
        finish()
    }


    private fun generateUserName(){
        et_user_name.isEnabled = false
        et_user_email.isEnabled = false

        (BASE_URL + PARAMS_NAME)
            .httpGet()
            .responseString { _, _, result ->
                result.failure {
                    setNameField("")
                    setEmailField("")
                }
                result.success {
                    val data = result.get()
                    val userData = APIManager().parseUserName(data)

                    if (UserHelper().isNameValid(userData.name) && UserHelper().isEmailValid(userData.email)){
                        setNameField(UserHelper().modifyNameFormat(userData.name))
                        setEmailField(userData.email)
                    } else {
                        setNameField("")
                        setEmailField("")
                    }
                }
            }
    }


    private fun setNameField(name : String){
        et_user_name.setText(name)
        et_user_name.isEnabled = true
        et_user_name.setSelection(et_user_name.text.length)
    }

    private fun setEmailField(email : String){
        et_user_email.setText(email)
        et_user_email.isEnabled = true
        et_user_email.setSelection(0)
    }
}
