package com.expert.operrwork.view.oldDashboard.timeSheet.timeSheetTimeNoteList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R

/**
 * Created by Akshay.
 * seller product list adapter
 * @property mProductClickListener click Listener
 */
class TimeSheetNoteListAdapter(val mContext: Context, var mList: ArrayList<String>, var mProductClickListener: ItemClick) : RecyclerView.Adapter<TimeSheetNoteListAdapter.DashBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_time_edit_note, parent, false)
        return DashBoardViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val btnName = mList[position]
        holder.timeDayDate.text = btnName
        holder.itemView.setOnClickListener {
            mProductClickListener.buttonClick(position)
        }
    }

    class DashBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeDayDate = itemView.findViewById<AppCompatTextView>(R.id.txt_dispute_time_day_date)!!
    }


    interface ItemClick {
        fun buttonClick(Position: Int)
    }
}