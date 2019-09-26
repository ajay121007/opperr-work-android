package com.expert.operrwork.view.oldDashboard.requestOffTime

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.data.model.demo.DayAvailable

/**
 * Created by Akshay.
 * seller product list adapter
 * @property mClickListener click Listener
 */
class RequestOffDaysAvailableAdapter(val mContext: Context, var mList: List<DayAvailable>, var mClickListener: ClickListener) : RecyclerView.Adapter<RequestOffDaysAvailableAdapter.DashBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_request_off_remaining_days, parent, false)
        return DashBoardViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val dayAvailable = mList[position]
        holder.types.text = dayAvailable.leaveType
        holder.days.text = dayAvailable.daysRemaining

        holder.itemView.setOnClickListener {
            mClickListener.itemClick(position)
        }
    }

    class DashBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val types = itemView.findViewById<AppCompatTextView>(R.id.txt_available_days_type)!!
        val days = itemView.findViewById<AppCompatTextView>(R.id.txt_available_days)!!
    }

    interface ClickListener {
        fun itemClick(position: Int)
    }
}