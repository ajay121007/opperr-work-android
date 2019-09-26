package com.expert.operrwork.view.userAccess

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.view.userAccess.login.LoginFragment
import com.expert.operrwork.view.userAccess.registration.RegisterEmployeeFragment
import kotlinx.android.synthetic.main.fragment_user_access_panel.*


/**
 * Created by Akshay.
 */
@SuppressLint("NewApi")
class UserAccessFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_access_panel, container, false)
    }

    @SuppressLint("NewApi")
    override fun onStart() {
        super.onStart()
        activity!!.window.statusBarColor = ContextCompat.getColor(activity!!, android.R.color.transparent)
        (activity as AppCompatActivity).supportActionBar!!.hide()

    }

    override fun onStop() {
        super.onStop()
        activity!!.window.statusBarColor = ContextCompat.getColor(activity!!, R.color.colorPrimaryDark)
        (activity as AppCompatActivity).supportActionBar!!.show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        txt_register.setOnClickListener {
            addReplaceFragment(R.id.fl_user_access,
                RegisterEmployeeFragment(), false, true, null
            )
        }

        btn_login.setOnClickListener {
            addReplaceFragment(
                R.id.fl_user_access, LoginFragment(), false, true, null)
        }
    }
}