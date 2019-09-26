package com.expert.operrwork.view.oldDashboard.timeSheet.timeSheetTimeNoteList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.view.oldDashboard.timeSheet.timeAddnote.TimeSheetAddNoteFragment
import com.expert.operrwork.data.model.demo.TimeSheetDispute
import com.expert.operrwork.util.Constant
import kotlinx.android.synthetic.main.fragment_time_sheet_note_list.*

/**
 * Created by Akshay.
 */
class TimeSheetNoteListFragment : BaseFragment() {

    var mTimeSheetDispute = TimeSheetDispute()
    var timeSheetNoteListAdapter: TimeSheetNoteListAdapter? = null
    var arrayList = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_time_sheet_note_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        txt_select_note_day_time.text = mTimeSheetDispute.mWeekName
        prepareArrayList()
        setRecyclerView()
    }

    private fun prepareArrayList() {
        arrayList.clear()
        for ((i, e) in Constant.DemoTimeArray.withIndex()) {
            arrayList.add(e)
        }
    }


    //set RecyclerView
    private fun setRecyclerView() {
        Log.d(Constant.Tag, Gson().toJson(arrayList))
        val layoutManager = LinearLayoutManager(context!!)
        rv_time_sheet_edit_note.layoutManager = layoutManager
        timeSheetNoteListAdapter = TimeSheetNoteListAdapter(context!!, arrayList, object : TimeSheetNoteListAdapter.ItemClick {
            override fun buttonClick(Position: Int) {
                val mTimeSheetAddNoteFragment=TimeSheetAddNoteFragment()
                mTimeSheetAddNoteFragment.mTimeSheetDispute=mTimeSheetDispute
                addReplaceFragment(R.id.fl_container, mTimeSheetAddNoteFragment, false, true, null)
            }
        })
        rv_time_sheet_edit_note.adapter = timeSheetNoteListAdapter
        timeSheetNoteListAdapter!!.notifyDataSetChanged()
    }
}