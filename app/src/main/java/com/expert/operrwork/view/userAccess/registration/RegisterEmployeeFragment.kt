package com.expert.operrwork.view.userAccess.registration

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register_employee.*


/**
 * Created by Akshay.
 */
class RegisterEmployeeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register_employee, container, false)
    }

    @SuppressLint("InlinedApi", "ResourceAsColor")
    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.tb_register_employee)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {

        btn_register_continue.setOnClickListener {
            addReplaceFragment(R.id.fl_container,
                AddProfileFragment(), false, true, null)
        }
    }
}