package com.expert.operrwork.view.userAccess.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.login.LoginRequest
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.util.Constant
import com.expert.operrwork.util.SharedPref
import com.expert.operrwork.view.MainActivity
import com.expert.operrwork.view.userAccess.UserAccessViewModel
import com.expert.operrwork.view.userAccess.forgotPassword.ForgotPasswordFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


/**
 * Created by Akshay.
 */
class LoginFragment : BaseFragment() {

    val sharedPref: SharedPref by inject()
    var userAccessViewModel: UserAccessViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.expert.operrwork.R.layout.fragment_login, container, false)
    }

    @SuppressLint("InlinedApi", "ResourceAsColor")
    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title =
            resources.getString(com.expert.operrwork.R.string.tb_app_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userAccessViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(UserAccessViewModel::class.java)
        initObserver()
    }

    private fun initObserver() {
        userAccessViewModel!!.getLoginResponse().observe(this, Observer {
            if (it != null) {
                if (it.status.equals("SUCCESS")) {
                    if (cb_remember_me.isChecked) {
                        sharedPref.setBoolean(Constant.IS_LOGIN, true)
                    }
                    sharedPref.setString(Constant.TOKEN, it.data?.token!!)
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity!!.finish()
                } else {
                    showError(it.message!!)
                }

            }
        })
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {

        txt_forgot_password.setOnClickListener {
            val mForgotPasswordFragment =
                ForgotPasswordFragment()
            mForgotPasswordFragment.previousToolbarName =
                resources.getString(com.expert.operrwork.R.string.tb_app_login)
            addReplaceFragment(
                com.expert.operrwork.R.id.fl_user_access,
                mForgotPasswordFragment,
                false,
                true,
                null
            )
        }

        btn_login.setOnClickListener {
            if (edt_login_username.text.toString().isEmpty()) {
                showError("Please enter username")
            } else if (edt_login_password.text.toString().isEmpty()) {
                showError("Please enter password")
            } else {
                val loginRequest = LoginRequest()
                loginRequest.username = edt_login_username.text.toString()
                loginRequest.password = MD5_Hash(edt_login_password.text.toString())
                userAccessViewModel?.getLogin(loginRequest)
            }
        }
    }

    private fun MD5_Hash(s: String): String {
        var m: MessageDigest? = null

        try {
            m = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        m!!.update(s.toByteArray(), 0, s.length)
        return BigInteger(1, m.digest()).toString(16)
    }
}