package com.asadmansoor.montage.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import com.asadmansoor.montage.api.APIManager
import com.asadmansoor.montage.helper.NameHelper
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import kotlinx.android.synthetic.main.activity_generate_user.*
import java.util.*

private const val IMG_BOUND = 14
private const val COLOR_BOUND = 4

private const val BASE_URL = "https://randomuser.me/api/"
private const val PARAMS_NAME = "?inc=gender,name,nat&nat=au,gb,us,ca,nz"
private const val DEFAULT_INDEX = 0

class GenerateUserActivity : AppCompatActivity() {

    private var userName: String? = null
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
            cancel()
        }
    }


    private fun setUserProfile(){
        val intent = intent
        userName = intent.getStringExtra(UserProperties.EXTRA_NAME)
        imgIndex = intent.getIntExtra(UserProperties.EXTRA_IMG_RES, DEFAULT_INDEX)
        colorIndex = intent.getIntExtra(UserProperties.EXTRA_COLOR_RES, DEFAULT_INDEX)

        if (userName == null){
            generateUserName()
            generateUserImg()
            generateColorIndex()
        } else {
            iv_user_image.setImageResource(UserProperties.imgResource[imgIndex!!])
            setUserField(userName!!)
        }
    }


    private fun generateUserImg(){
        imgIndex = generateRandomIndex(IMG_BOUND)
        val randomIndex = imgIndex as Int
        iv_user_image.setImageResource(UserProperties.imgResource[randomIndex])
    }


    private fun generateColorIndex(){
        colorIndex = generateRandomIndex(COLOR_BOUND)
    }


    private fun generateRandomIndex(bound: Int) : Int {
        return Random().nextInt(bound)
    }


    private fun saveNewUser(){
        userName = et_user_name.text.toString()

        val intent = Intent(this, UserInformationActivity::class.java)
        intent.putExtra(UserProperties.EXTRA_NAME, userName)
        intent.putExtra(UserProperties.EXTRA_IMG_RES, imgIndex)
        intent.putExtra(UserProperties.EXTRA_COLOR_RES, colorIndex)

        intent.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        startActivity(intent)
        finish()
    }


    private fun cancel(){
        val intent = Intent()
        setResult(Activity.RESULT_CANCELED, intent)
        finish()
    }


    private fun generateUserName(){
        et_user_name.isEnabled = false

        (BASE_URL + PARAMS_NAME)
            .httpGet()
            .responseString { _, _, result ->
                result.failure {
                    setUserField("")
                }
                result.success {
                    val data = result.get()
                    val userData = APIManager().parseUserName(data)
                    if (NameHelper().isNameValid(userData)){
                        setUserField(NameHelper().modifyNameFormat(userData))
                    } else {
                        setUserField("")
                    }
                }
            }
    }


    private fun setUserField(name : String){
        et_user_name.setText(name)
        et_user_name.isEnabled = true
        et_user_name.setSelection(et_user_name.text.length)
    }
}
