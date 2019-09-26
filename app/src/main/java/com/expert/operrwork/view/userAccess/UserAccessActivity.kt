package com.expert.operrwork.view.userAccess

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseActivity
import kotlinx.android.synthetic.main.activity_user_access.*


class UserAccessActivity : BaseActivity() {
    private var doubleBackToExitPressedOnce: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadeinlogin, R.anim.fadeout);
        setContentView(R.layout.activity_user_access)
        setSupportActionBar(user_access_toolbar)

        //back button Action perform in toolbar if fragment are loaded
        user_access_toolbar.setNavigationOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            }
        }

        //back button show in toolbar if fragment are loaded
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                supportActionBar!!.setTitle(R.string.app_name)

            }
        }
        addReplaceFragment(R.id.fl_user_access,
            UserAccessFragment(), false, false, null)
    }

    @SuppressLint("NewApi")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else if (!doubleBackToExitPressedOnce) {
            doubleBackToExitPressedOnce = true
            showError(resources.getString(R.string.msg_press_back_to_exit))
            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        } else {
            super.onBackPressed()
            finishAffinity()
        }
    }
}