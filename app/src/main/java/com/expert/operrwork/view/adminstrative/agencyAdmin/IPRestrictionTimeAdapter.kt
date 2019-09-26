package com.expert.operrwork.view.adminstrative.agencyAdmin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.data.model.agency.IPRestrictionTimeBean
import java.util.ArrayList


class IPRestrictionTimeAdapter(
    internal var items: List<IPRestrictionTimeBean.IPListBean>
) : RecyclerView.Adapter<IPRestrictionTimeAdapter.ViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_restriction_time, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewItemName.text = items[position].ip
        holder.textViewDays.text = items[position].days
        holder.textViewTime.text = items[position].startTime+"-"+items[position].endTime


        holder.switchActive.isChecked = items[position].checked

        if (items[position].checked) {
            //holder.itemView.visibility=View.VISIBLE
            //holder.itemView.layoutParams =
            //    RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        } else {
            //holder.itemView.visibility=View.GONE
           // holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
        holder.switchActive.setOnClickListener {
            if (!items[position].checked) {
                holder.switchActive.isChecked = true
                items[position].checked = true
            } else {
                holder.switchActive.isChecked = false
                items[position].checked = false
            }
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun customNotify(ipList: ArrayList<IPRestrictionTimeBean.IPListBean>) {
        this.items=ipList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewItemName: TextView
        var textViewDays: TextView
        var textViewTime: TextView
        var image: ImageView
        var switchActive: SwitchCompat
        var rootLayout: LinearLayout

        init {
            textViewItemName = itemView.findViewById(R.id.textViewItemName)
            switchActive = itemView.findViewById(R.id.switchActive)
            rootLayout = itemView.findViewById(R.id.rootLayout)
            textViewDays = itemView.findViewById(R.id.textViewDays)
            textViewTime = itemView.findViewById(R.id.textViewTime)
            image = itemView.findViewById(R.id.image)
        }
    }

}
