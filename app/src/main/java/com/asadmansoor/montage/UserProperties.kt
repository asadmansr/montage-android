package com.asadmansoor.montage


class UserProperties {
    companion object {
        val imgResource: IntArray = intArrayOf(
            R.drawable.man_1, R.drawable.man_2, R.drawable.man_3, R.drawable.man_4, R.drawable.man_5, R.drawable.man_6, R.drawable.man_7,
            R.drawable.woman_1, R.drawable.woman_2, R.drawable.woman_3, R.drawable.woman_4, R.drawable.woman_5, R.drawable.woman_6, R.drawable.woman_7)

        val colorResource = arrayOf("#4285F4", "#DB4437", "#F4B400","#0F9D58")

        const val EXTRA_NAME = "com.asadmansoor.montage.EXTRA_NAME"
        const val EXTRA_EMAIL = "com.asadmansoor.montage.EXTRA_EMAIL"
        const val EXTRA_IMG_RES = "com.asadmansoor.montage.EXTRA_IMG_RES"
        const val EXTRA_COLOR_RES = "com.asadmansoor.montage.EXTRA_COLOR_RES"
        const val EXTRA_USER_ARR = "com.asadmansoor.montage.EXTRA_USER"

        const val EXTRA_USERNAME = "com.asadmansoor.montage.EXTRA_USERNAME"
        const val EXTRA_PASSWORD = "com.asadmansoor.montage.EXTRA_PASSWORD"
        const val EXTRA_PHONE = "com.asadmansoor.montage.EXTRA_PHONE"
        const val EXTRA_CITY = "com.asadmansoor.montage.EXTRA_CITY"
        const val EXTRA_STATE = "com.asadmansoor.montage.EXTRA_STATE"
        const val EXTRA_TIMEZONE = "com.asadmansoor.montage.EXTRA_TIMEZONE"
    }
}