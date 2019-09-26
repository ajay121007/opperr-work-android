package com.expert.operrwork.view.oldDashboard.payment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment

/**
 * Created by Akshay.
 */
class PayStubsPaymentsFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pay_stubs_payment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity!!.menuInflater.inflate(R.menu.pay_stubs_payment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_payment) {
            openPaymentEarningFragment()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun openPaymentEarningFragment() {
        addReplaceFragment(R.id.fl_container, PayStubsEarningFragment(), false, true, null)
    }
}