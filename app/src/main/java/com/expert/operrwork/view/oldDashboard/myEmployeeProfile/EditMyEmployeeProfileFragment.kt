package com.expert.operrwork.view.oldDashboard.myEmployeeProfile

import android.os.Bundle
import android.view.*
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment


/**
 * Created by Akshay.
 */
class EditMyEmployeeProfileFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_my_employee_profile, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity!!.menuInflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_qr_code) {
            openQrCodeFragment()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun openQrCodeFragment() {
        addReplaceFragment(R.id.fl_container, MyScanKeyFragment(), false, true, null)
    }
}