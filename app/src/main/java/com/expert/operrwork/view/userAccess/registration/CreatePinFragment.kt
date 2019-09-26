package com.expert.operrwork.view.userAccess.registration

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_create_pin.*


/**
 * Created by Akshay.
 */
class CreatePinFragment : BaseFragment() {

    var mSideMenu = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_pin, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (mSideMenu) {
            (activity as AppCompatActivity).supportActionBar!!.title =resources.getString(R.string.tb_reset_pass_code)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        btn_create_pin_continue.setOnClickListener {
            if (!mSideMenu) addReplaceFragment(R.id.fl_container,
                CompanyPolicyFragment(), false, true, null)
            else fragmentManager!!.popBackStackImmediate()
        }

        edt_date_time_key.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    edt_second_key.requestFocus()
                }
            }

        })

        edt_second_key.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    edt_third_key.requestFocus()
                }
            }

        })

        edt_third_key.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    edt_four_key.requestFocus()
                }
            }

        })

        edt_four_key.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    hideKeyboard(activity!!)
                }
            }

        })
    }
}