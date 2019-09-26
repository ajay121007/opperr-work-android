package com.expert.operrwork.view.adminstrative.agencyAdmin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.data.model.group.getAllGroup.ContentItem

class GroupDialogAdapter(
    internal var items: List<ContentItem?>?
) : RecyclerView.Adapter<GroupDialogAdapter.ViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_menu_dialog, null)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewItemName.text = this.items!![position]!!.name


        holder.checkBox.isChecked = items!![position]!!.checked!!
        holder.itemView.setOnClickListener {
            if (!items!![position]!!.checked!!) {
                holder.checkBox.isChecked = true
                items!![position]?.checked = true
            } else {
                holder.checkBox.isChecked = false
                items!![position]?.checked = false
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewItemName: TextView
        var checkBox: CheckBox

        init {
            textViewItemName = itemView.findViewById(R.id.textViewItemName)
            checkBox = itemView.findViewById(R.id.checkBox)
        }
    }

}
