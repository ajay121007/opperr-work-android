package com.expert.operrwork.view.adminstrative.agencyAdmin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.data.model.agency.DaysBean


class DaysAdapter(
    internal var items: ArrayList<DaysBean.DaysListBean>?
) : RecyclerView.Adapter<DaysAdapter.ViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_days, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewItemName.text = items?.get(position)?.days

        holder.itemView.setOnClickListener {
            if (!items?.get(position)!!.checked) {
                holder.checkBox.isChecked = true
                items!![position].checked = true
            } else {
                holder.checkBox.isChecked = false
                this.items!![position].checked = false
            }
            notifyDataSetChanged()
        }
        holder.checkBox.setOnClickListener {
            if (!items?.get(position)!!.checked) {
                holder.checkBox.isChecked = true
                items!![position].checked = true
            } else {
                holder.checkBox.isChecked = false
                this.items!![position].checked = false
            }
            notifyDataSetChanged()
        }


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewItemName: TextView
        var checkBox: AppCompatCheckBox
        var rootLayout: LinearLayout

        init {
            textViewItemName = itemView.findViewById(R.id.textViewItemName)
            rootLayout = itemView.findViewById(R.id.rootLayout)
            checkBox = itemView.findViewById(R.id.checkBox)
        }
    }



}
