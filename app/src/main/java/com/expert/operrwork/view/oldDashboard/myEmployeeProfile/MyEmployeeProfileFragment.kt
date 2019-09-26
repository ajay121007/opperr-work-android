package com.expert.operrwork.view.oldDashboard.myEmployeeProfile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_employee_profile.*


/**
 * Created by Akshay.
 */
class MyEmployeeProfileFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_employee_profile, container, false)
    }

    @SuppressLint("InlinedApi")
    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.tb_my_employee_profile)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        btn_my_employee_edit.setOnClickListener {
            addReplaceFragment(R.id.fl_container, EditMyEmployeeProfileFragment(), false, true, null)
        }
    }
}