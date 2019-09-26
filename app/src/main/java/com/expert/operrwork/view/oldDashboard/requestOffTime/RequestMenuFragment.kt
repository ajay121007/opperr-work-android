package com.expert.operrwork.view.oldDashboard.requestOffTime

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.view.oldDashboard.DashboardAdapter
import kotlinx.android.synthetic.main.fragment_request_menu.*


/**
 * Created by Akshay.
 */
class RequestMenuFragment : BaseFragment() {

    var mDashboardAdapter: DashboardAdapter? = null
    var mData = ArrayList<String>()
    private var mBottomSheetBehaviour: BottomSheetBehavior<*>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_request_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun init() {
        mData.clear()
        resources.getStringArray(R.array.request_off_menu).toCollection(mData)
        setDashboardRecyclerView()
    }

    //after getting data it update the Product in recyclerView
    private fun setDashboardRecyclerView() {
        val layoutManager = LinearLayoutManager(context!!)
        rv_request_menus.layoutManager = layoutManager
        mDashboardAdapter = DashboardAdapter(context!!, mData, object : DashboardAdapter.ProductClickListener {
            override fun itemClick(position: Int) {
                when {
                    mData[position].contentEquals(resources.getString(R.string.Days_Available)) -> addReplaceFragment(R.id.fl_container, DaysAvailableFragment(), false, true, null)
                    else -> addReplaceFragment(R.id.fl_container, RequestHistoryDayOffFragment(), false, true, null)
                }
            }
        })
        rv_request_menus.adapter = mDashboardAdapter
        mDashboardAdapter!!.notifyDataSetChanged()
    }
}