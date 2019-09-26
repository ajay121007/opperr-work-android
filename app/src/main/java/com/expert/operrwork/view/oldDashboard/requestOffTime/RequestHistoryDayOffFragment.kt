package com.expert.operrwork.view.oldDashboard.requestOffTime

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.view.oldDashboard.timeSheet.timeSheetDispute.TimeSheetDisputeFragment
import com.expert.operrwork.util.Constant
import kotlinx.android.synthetic.main.fragment_request_off_history_off.*


/**
 * Created by Akshay.
 */
class RequestHistoryDayOffFragment : BaseFragment() {

    var listDataHeader: ArrayList<String>? = null
    var mAdapterRequest: RequesHistoryOffAdapter? = null
    var listDataChild: HashMap<String, List<String>>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_request_off_history_off, container, false)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.tb_time_sheet)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareListData()
        setExpandableListViewIndicatorRight()
        init()
    }

    private fun init() {
        mAdapterRequest = RequesHistoryOffAdapter(context!!, listDataHeader!!, listDataChild!!, object : RequesHistoryOffAdapter.ItemClick {
            override fun buttonClick(groupPosition: Int, childPosition: Int) {
                Log.d(Constant.Tag, "Header-->" + listDataHeader!![groupPosition])
                Log.d(Constant.Tag, "Child-->" + listDataChild!![listDataHeader!![groupPosition]]!![childPosition])
                val mTimeSheetDisputeFragment = TimeSheetDisputeFragment()
                mTimeSheetDisputeFragment.mHeader = listDataHeader!![groupPosition]
                mTimeSheetDisputeFragment.mChild = listDataChild!![listDataHeader!![groupPosition]]!![childPosition]
                addReplaceFragment(R.id.fl_container, mTimeSheetDisputeFragment, false, true, null)
            }
        })
        el_history_off.setChildIndicator(null)
        el_history_off.setChildDivider(resources.getDrawable(R.color.white))
        el_history_off.setAdapter(mAdapterRequest)
    }

    //right icon on right in Expandable list view
    private fun setExpandableListViewIndicatorRight() {
        val metrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            el_history_off.setIndicatorBounds(width - getPixelFromDips(60f), width- getPixelFromDips(30f))
        } else {
            el_history_off.setIndicatorBoundsRelative(width - getPixelFromDips(60f), width - getPixelFromDips(30f))
        }
    }

    private fun getPixelFromDips(pixels: Float): Int {
        // Get the screen's density scale
        val scale = resources.displayMetrics.density
        // Convert the dps to pixels, based on density scale
        return (pixels * scale + 0.5f).toInt()
    }

    /*
   * Preparing the list data
   */
    private fun prepareListData() {
        listDataHeader = ArrayList<String>()
        listDataChild = HashMap<String, List<String>>()

        for (i in Constant.DemoLeaveType) {
            listDataHeader!!.add("" + i)
        }

        for ((count, i) in (Constant.DemoLeaveType).withIndex()) {
            val listChild = ArrayList<String>()
            Constant.DemoHistoryDays.toCollection(listChild)
            listDataChild!![listDataHeader!![count]] = listChild
        }
    }
}