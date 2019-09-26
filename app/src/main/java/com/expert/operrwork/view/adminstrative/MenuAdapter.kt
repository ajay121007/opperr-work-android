package com.expert.operrwork.view.adminstrative

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.data.model.menu.MenuItem





class MenuAdapter(
    internal var items: List<MenuItem.MenuListBean>
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(com.expert.operrwork.R.layout.adapter_menu_list, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewItemName.text = items[position].name


        holder.checkBox.isChecked = items[position].checked

        if (items[position].checked) {
            holder.itemView.visibility=View.VISIBLE
            holder.itemView.layoutParams =
                RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        } else {
            holder.itemView.visibility=View.GONE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
        holder.checkBox.setOnClickListener {
            if (!items[position].checked) {
                holder.checkBox.isChecked = true
                items[position].checked = true
            } else {
                holder.checkBox.isChecked = false
                items[position].checked = false
            }
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewItemName: TextView
        var checkBox: CheckBox
        var rootLayout: LinearLayout

        init {
            textViewItemName = itemView.findViewById(com.expert.operrwork.R.id.textViewItemName)
            checkBox = itemView.findViewById(com.expert.operrwork.R.id.checkBox)
            rootLayout = itemView.findViewById(com.expert.operrwork.R.id.rootLayout)
        }
    }

}
