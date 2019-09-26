package com.expert.operrwork.view.oldDashboard.timeSheet.timeSheetDispute

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.view.oldDashboard.timeSheet.timeSheetTimeNoteList.TimeSheetNoteListFragment
import com.expert.operrwork.data.model.demo.TimeSheetDispute
import com.expert.operrwork.util.Constant
import kotlinx.android.synthetic.main.fragment_time_sheet_dispute.*

/**
 * Created by Akshay.
 */
class TimeSheetDisputeFragment : BaseFragment() {

    var mHeader: String = ""
    var mChild: String = ""
    var arraylist = ArrayList<TimeSheetDispute>()
    var mTimeSheetDisputeAdapter: TimeSheetDisputeAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_time_sheet_dispute, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        txt_month.text = mChild
        prepareData()
        setRecyclerView()
    }

    //set RecyclerView
    private fun setRecyclerView() {
        Log.d(Constant.Tag, Gson().toJson(arraylist))
        val layoutManager = LinearLayoutManager(context!!)
        rv_dispute_time.layoutManager = layoutManager
        mTimeSheetDisputeAdapter = TimeSheetDisputeAdapter(context!!, arraylist, object : TimeSheetDisputeAdapter.ItemClick {
            override fun buttonClick(Position: Int) {
                val mTimeSheetEditNoteFragment = TimeSheetNoteListFragment()
                mTimeSheetEditNoteFragment.mTimeSheetDispute = arraylist[Position]
                addReplaceFragment(R.id.fl_container, mTimeSheetEditNoteFragment, false, true, null)
            }
        })
        rv_dispute_time.adapter = mTimeSheetDisputeAdapter
        mTimeSheetDisputeAdapter!!.notifyDataSetChanged()
    }

    private fun prepareData() {
        arraylist.clear()
        for ((i, e) in (Constant.DemoWeekArray).withIndex()) {
            arraylist.add(TimeSheetDispute(e +" "+ currentDateWithWeekName(Constant.mDateOnlyDateFormat), "", "", "", "", 0))
            for ((j, f) in (Constant.DemoInOutArray).withIndex()) {
                if (j % 2 == 0) arraylist.add(TimeSheetDispute(e, Constant.DemoTimeArray[j], f, "", "", 1))
                else arraylist.add(TimeSheetDispute(e, Constant.DemoTimeArray[j], f, resources.getString(R.string.msg_note_need), "", 2))
            }
            arraylist.add(TimeSheetDispute(e, "", "", "", resources.getString(R.string.time_total), 3))
        }
    }
}