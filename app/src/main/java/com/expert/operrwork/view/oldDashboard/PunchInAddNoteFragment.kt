package com.expert.operrwork.view.oldDashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.util.Constant
import kotlinx.android.synthetic.main.fragment_add_a_note.*

/**
 * Created by Akshay.
 */
class PunchInAddNoteFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_a_note, container, false)
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
        edt_date_time_key.text = "${currentDateWithWeekName(Constant.mPunchInDateFormat)} \n ${currentTime(Constant.mCommonTimeFormat)}"
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.tb_clock_in)
    }
}