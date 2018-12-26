package com.asadmansoor.montage.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import kotlinx.android.synthetic.main.activity_generate_user.*
import java.util.*

private const val IMG_BOUND = 14
private const val COLOR_BOUND = 4

class GenerateUserActivity : AppCompatActivity() {

    private var imgIndex: Int? = null
    private var colorIndex: Int? = null

    companion object {
        const val EXTRA_NAME = "com.asadmansoor.montage.ui.EXTRA_NAME"
        const val EXTRA_IMG_RES = "com.asadmansoor.montage.ui.EXTRA_IMG_RES"
        const val EXTRA_COLOR_RES = "com.asadmansoor.montage.ui.EXTRA_COLOR_RES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_user)
        generateUserImg()
        generateColorIndex()

        btn_generate_img.setOnClickListener {
            generateUserImg()
            generateColorIndex()
        }

        btn_generate_save.setOnClickListener {
            saveNewUser()
        }

        btn_generate_close.setOnClickListener {
            cancel()
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
        val userName = et_user_name.text.toString()

        val intent = Intent()
        intent.putExtra(EXTRA_NAME, userName)
        intent.putExtra(EXTRA_IMG_RES, imgIndex)
        intent.putExtra(EXTRA_COLOR_RES, colorIndex)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun cancel(){
        val intent = Intent()
        setResult(Activity.RESULT_CANCELED, intent)
        finish()
    }
}
