package com.expert.operrwork.view.splashActivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.expert.operrwork.view.MainActivity
import com.expert.operrwork.R
import com.expert.operrwork.view.userAccess.UserAccessActivity
import com.expert.operrwork.util.Constant
import com.expert.operrwork.util.SharedPref
import org.koin.android.ext.android.inject

class SplashScreen : AppCompatActivity() {
    val sharedPref: SharedPref by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            if (!sharedPref.getBoolean(Constant.IS_LOGIN)) {
                startActivity(Intent(this@SplashScreen, UserAccessActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            }
            this@SplashScreen.finish()
        }, 2000)
    }


}