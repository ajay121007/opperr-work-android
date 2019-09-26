package com.expert.operrwork.view.oldDashboard.requestOffTime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.demo.DayAvailable
import com.expert.operrwork.util.Constant
import com.expert.operrwork.util.Constant.Companion.DemoLeaveType
import kotlinx.android.synthetic.main.fragment_request_off_day_available.*


/**
 * Created by Akshay.
 */
class DaysAvailableFragment : BaseFragment() {

    var mRequestOffDaysAvailableAdapter: RequestOffDaysAvailableAdapter? = null
    var mData = ArrayList<DayAvailable>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_request_off_day_available, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        prepareData()
        setRecyclerView()
    }

    private fun prepareData() {
        mData.clear()
        for ((i, e) in DemoLeaveType.withIndex()) {
            mData.add(DayAvailable(e, Constant.DemoLeaveDays[i]))
        }
    }

    //after getting data it update the Product in recyclerView
    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(context!!)
        rv_request_off_days.layoutManager = layoutManager
        mRequestOffDaysAvailableAdapter = RequestOffDaysAvailableAdapter(context!!, mData, object : RequestOffDaysAvailableAdapter.ClickListener {
            override fun itemClick(position: Int) {
                when {

                }
            }
        })
        rv_request_off_days.adapter = mRequestOffDaysAvailableAdapter
        mRequestOffDaysAvailableAdapter!!.notifyDataSetChanged()
    }
}