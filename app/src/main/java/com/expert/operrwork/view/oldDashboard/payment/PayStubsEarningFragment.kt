package com.expert.operrwork.view.oldDashboard.payment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.demo.PayStubsPayment
import com.expert.operrwork.data.model.demo.PayStubsTaxesPayment
import com.expert.operrwork.util.Constant
import kotlinx.android.synthetic.main.fragment_pay_stubs_earnings.*

/**
 * Created by Akshay.
 */
class PayStubsEarningFragment : BaseFragment() {
    var mTaxesArrayList = ArrayList<PayStubsTaxesPayment>()
    var mEarningArrayList = ArrayList<PayStubsPayment>()
    var mPaymentTaxesAdapter:PaymentTaxesAdapter?=null
    var mPayStubsEarningAdapter:PayStubsEarningAdapter?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pay_stubs_earnings, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        preparedData()
        initTaxesRecyclerView()
        initPaymentRecyclerView()
    }


    private fun preparedData() {
        mTaxesArrayList.clear()
        for ((i, e) in Constant.DemoTaxesTypes.withIndex()) {
            mTaxesArrayList.add(PayStubsTaxesPayment(e, Constant.DemoCurrent[i], Constant.DemoCurrent[i]))
        }
        mEarningArrayList.clear()
        for ((i, e) in Constant.DemoTypes.withIndex()) {
            if (e == "Total" || e == "NetPay") mEarningArrayList.add(PayStubsPayment(e, Constant.DemoHr[i], Constant.DemoHrs[i], Constant.DemoCurrent[i], Constant.DemoCurrent[i], Constant.DemoCurrent[i], Constant.DemoCurrent[i], 1))
            else mEarningArrayList.add(PayStubsPayment(e, Constant.DemoHr[i], Constant.DemoHrs[i], Constant.DemoCurrent[i], Constant.DemoCurrent[i], Constant.DemoCurrent[i], Constant.DemoCurrent[i], 0))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initTaxesRecyclerView() {
        Log.d(Constant.Tag, Gson().toJson(mTaxesArrayList))
        val layoutManager = LinearLayoutManager(context!!)
        rv_pay_stubs_taxes.layoutManager = layoutManager
        mPaymentTaxesAdapter = PaymentTaxesAdapter(context!!, mTaxesArrayList, object : PaymentTaxesAdapter.ItemClickListener {
            override fun itemClick(position: Int) {

            }
        })
        rv_pay_stubs_taxes.adapter = mPaymentTaxesAdapter
        mPaymentTaxesAdapter!!.notifyDataSetChanged()
    }

    private fun initPaymentRecyclerView() {
        Log.d(Constant.Tag, Gson().toJson(mEarningArrayList))
        val layoutManager = LinearLayoutManager(context!!)
        rv_pay_stubs_earnings.layoutManager = layoutManager
        mPayStubsEarningAdapter = PayStubsEarningAdapter(context!!, mEarningArrayList, object : PayStubsEarningAdapter.ItemClick {
            override fun buttonClick(Position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        rv_pay_stubs_earnings.adapter = mPayStubsEarningAdapter
        mPayStubsEarningAdapter!!.notifyDataSetChanged()
    }


}