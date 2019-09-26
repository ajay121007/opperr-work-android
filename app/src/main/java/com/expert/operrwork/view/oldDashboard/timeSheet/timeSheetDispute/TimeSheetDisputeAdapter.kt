package com.expert.operrwork.view.oldDashboard.timeSheet.timeSheetDispute

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.data.model.demo.TimeSheetDispute
import com.expert.operrwork.util.Constant


/**
 * Created by Akshay.
 */
@Suppress("UNREACHABLE_CODE")
class TimeSheetDisputeAdapter(private val mContext: Context, private val mArrayList: ArrayList<TimeSheetDispute>, var itemClick: ItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        Log.d(Constant.Tag, "-->$viewType")
        return when (viewType) {
            0 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_time_edit_note, parent, false)
                return DisputeDayViewHolder(view)
            }
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_dispute_time_detail, parent, false)
                return DisputeTimeViewHolder(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_dispute_time_detail_extended, parent, false)
                return DisputeTimeWithNoteViewHolder(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_dispute_time_ot, parent, false)
                return DisputeTotalViewHolder(view)
            }
            else -> null!!
        }
    }


    override fun getItemCount(): Int {
        return mArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mArrayList[position].mViewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (mArrayList[position].mViewType) {
            0 -> {
                val mDayHolder = (holder as DisputeDayViewHolder)
                mDayHolder.bind(position)
                mDayHolder.mDisputeDay.text = mArrayList[position].mWeekName
                mDayHolder.mEditIcon.setOnClickListener {
                    itemClick.buttonClick(position)
                }
            }
            1 -> {
                val mTimeHolder = (holder as DisputeTimeViewHolder)
                mTimeHolder.bind(position)
                mTimeHolder.mDisputeTime.text = mArrayList[position].mTime
                mTimeHolder.mDisputeTimeInOut.text = mArrayList[position].mTimeInOut
            }
            2 -> {
                (holder as DisputeTimeWithNoteViewHolder).also {
                    it.mDisputeExtendedTime.text = mArrayList[position].mTime
                    it.mDisputeExtendedTimeInOut.text = mArrayList[position].mTimeInOut
                    it.mDisputeExtendedTimeNote.text = mArrayList[position].mTimeNote
                }
            }
            3 -> {
                (holder as DisputeTotalViewHolder).also {
                    it.mDisputeTotal.text = mArrayList[position].mTimeOT
                }
            }
            else -> null!!
        }
    }

    class DisputeDayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mDisputeDay = view.findViewById<AppCompatTextView>(R.id.txt_dispute_time_day_date)!!
        val mEditIcon = view.findViewById<AppCompatImageView>(R.id.img_dispute_time_day_edit_icon)!!
        fun bind(position: Int) {

        }
    }

    class DisputeTimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mDisputeTime = view.findViewById<AppCompatTextView>(R.id.txt_dispute_time_time)!!
        val mDisputeTimeInOut = view.findViewById<AppCompatTextView>(R.id.txt_dispute_time_in_out)!!
        fun bind(position: Int) {

        }
    }

    class DisputeTimeWithNoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mDisputeExtendedTime = view.findViewById<AppCompatTextView>(R.id.txt_dispute_time_extended_time)!!
        val mDisputeExtendedTimeInOut = view.findViewById<AppCompatTextView>(R.id.txt_dispute_time_extended_in_out)!!
        val mDisputeExtendedTimeNote = view.findViewById<AppCompatTextView>(R.id.txt_dispute_time_extended_notes)!!
        fun bind(position: Int) {

        }
    }

    class DisputeTotalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mDisputeTotal = view.findViewById<AppCompatTextView>(R.id.txt_dispute_time_ot_total)!!
        fun bind(position: Int, mArrayList: ArrayList<TimeSheetDispute>) {

        }
    }

    interface ItemClick {
        fun buttonClick(Position: Int)
    }
}