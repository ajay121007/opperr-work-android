package com.expert.operrwork.view.userAccess.forgotPassword

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.forgotPassword.ForgotPasswordRequest
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.view.userAccess.UserAccessViewModel
import kotlinx.android.synthetic.main.fragment_forgot_password.*


/**
 * Created by Akshay.
 */
class ForgotPasswordFragment : BaseFragment() {

    var userAccessViewModel: UserAccessViewModel? = null
    var previousToolbarName = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userAccessViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(UserAccessViewModel::class.java)
        initObserver()
    }

    private fun initObserver() {
        userAccessViewModel!!.getForgotResponse().observe(this, Observer {
            if (it != null) {
                if (it.status.equals("SUCCESS")) {
                    edt_account_email.setText("")
                }
                showError(it.message!!)
            }
        })
    }

    @SuppressLint("InlinedApi", "ResourceAsColor")
    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.tb_account_help)
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.title = previousToolbarName
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {

        btn_forgot_submit.setOnClickListener {
            if (!mBaseActivity!!.isValidEmail(edt_account_email.text.toString())) {
                showError(getString(R.string.error_valid_email))
            } else {
                val request =
                    ForgotPasswordRequest()
                request.email = edt_account_email.text.toString()
                request.type = "admin"
                userAccessViewModel!!.forgotPassword(request)
            }
        }


    }
}