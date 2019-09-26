package com.expert.operrwork.view.oldDashboard.timeSheet.timeAddnote

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.demo.TimeSheetDispute
import kotlinx.android.synthetic.main.fragment_add_a_note.*

/**
 * Created by Akshay.
 */
class TimeSheetAddNoteFragment : BaseFragment() {

    var mTimeSheetDispute = TimeSheetDispute()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_time_shett_add_a_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {

        btn_add_a_note_submit.setOnClickListener {
            showError(resources.getString(R.string.msg_note_added_successful))
            fragmentManager!!.popBackStackImmediate()
        }

        btn_add_a_note_cancel.setOnClickListener {
            fragmentManager!!.popBackStackImmediate()
        }

        if (mTimeSheetDispute.mTime.isNotEmpty()) edt_date_time_key.text = mTimeSheetDispute.mWeekName + "\n" + mTimeSheetDispute.mTime
        else edt_date_time_key.text = mTimeSheetDispute.mWeekName
    }
}