package com.asadmansoor.montage.ui.UserDetail

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.asadmansoor.montage.R
import com.asadmansoor.montage.UserProperties
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailActivity : AppCompatActivity() {

    private val titleList = arrayListOf<String>("Name","Email","Username","Password","Phone","City","State","Timezone")
    private var contentList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)


        val intent = intent
        contentList = intent.getStringArrayListExtra(UserProperties.EXTRA_USER_ARR)
        val imgRes = intent.getIntExtra(UserProperties.EXTRA_IMG_RES, 0)
        val colorRes = intent.getIntExtra(UserProperties.EXTRA_COLOR_RES, 0)

        val adapter = UserDetailAdapter(this, titleList, contentList)
        lv_user_details.adapter = adapter

        iv_user_image.setImageResource(UserProperties.imgResource[imgRes])


        val userImageRef = picture_rl.background as GradientDrawable
        userImageRef.setColor(Color.parseColor(UserProperties.colorResource[colorRes]))
}


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_delete_all -> {
                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
