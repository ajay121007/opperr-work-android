package com.expert.operrwork.view.oldDashboard.timeSheet

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
import kotlinx.android.synthetic.main.fragment_time_sheet.*


/**
 * Created by Akshay.
 */
class TimeSheetFragment : BaseFragment() {

    var listDataHeader: ArrayList<String>? = null
    var listAdapter: ExpandableListAdapter? = null
    var listDataChild: HashMap<String, List<String>>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_time_sheet, container, false)
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
        listAdapter = ExpandableListAdapter(context!!, listDataHeader!!, listDataChild!!, object : ExpandableListAdapter.ItemClick {
            override fun buttonClick(groupPosition: Int, childPosition: Int) {
                Log.d(Constant.Tag, "Header-->" + listDataHeader!![groupPosition])
                Log.d(Constant.Tag, "Child-->" + listDataChild!![listDataHeader!![groupPosition]]!![childPosition])
                val mTimeSheetDisputeFragment = TimeSheetDisputeFragment()
                mTimeSheetDisputeFragment.mHeader = listDataHeader!![groupPosition]
                mTimeSheetDisputeFragment.mChild = listDataChild!![listDataHeader!![groupPosition]]!![childPosition]
                addReplaceFragment(R.id.fl_container, mTimeSheetDisputeFragment, false, true, null)
            }
        })
        el_time_sheet.setChildIndicator(null)
        el_time_sheet.setChildDivider(resources.getDrawable(R.color.white))
        el_time_sheet.setAdapter(listAdapter)
    }

    //right icon on right in Expandable list view
    private fun setExpandableListViewIndicatorRight() {
        val metrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            el_time_sheet.setIndicatorBounds(width - getPixelFromDips(60f), width- getPixelFromDips(30f))
        } else {
            el_time_sheet.setIndicatorBoundsRelative(width - getPixelFromDips(60f), width - getPixelFromDips(30f))
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

        for (i in 2019 downTo 2014) {
            listDataHeader!!.add("" + i)
        }

        for ((count, i) in (2019 downTo 2014).withIndex()) {
            val listChild = ArrayList<String>()
            listChild.add(resources.getString(R.string.january))
            listChild.add(resources.getString(R.string.february))
            listChild.add(resources.getString(R.string.march))
            listChild.add(resources.getString(R.string.april))
            listChild.add(resources.getString(R.string.may))
            listChild.add(resources.getString(R.string.june))
            listChild.add(resources.getString(R.string.july))
            listChild.add(resources.getString(R.string.august))
            listChild.add(resources.getString(R.string.september))
            listChild.add(resources.getString(R.string.october))
            listChild.add(resources.getString(R.string.november))
            listChild.add(resources.getString(R.string.december))
            listDataChild!![listDataHeader!![count]] = listChild
        }
    }
}