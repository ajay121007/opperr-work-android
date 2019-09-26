package com.expert.operrwork.view.operation

import android.annotation.SuppressLint
import android.os.Bundle
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseActivity
import kotlinx.android.synthetic.main.activity_operation.*

class OperationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_operation)
        setSupportActionBar(operation_toolbar)

        //back button Action perform in toolbar if fragment are loaded
        operation_toolbar.setNavigationOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }

        }

        //back button show in toolbar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        addReplaceFragment(R.id.fl_operation, OperationOptionFragment(), false, false, null)
    }



    @SuppressLint("NewApi")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
