package com.expert.operrwork.view.oldDashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R

/**
 * Created by Akshay.
 * seller product list adapter
 * @property mProductClickListener click Listener
 */
class DashboardAdapter(val mContext: Context, var mList: List<String>, var mProductClickListener: ProductClickListener) : RecyclerView.Adapter<DashboardAdapter.DashBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard_menu, parent, false)
        return DashBoardViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val btnName = mList[position]
        holder.btn.text = btnName
        holder.itemView.setOnClickListener {
            mProductClickListener.itemClick(position)
        }
        holder.btn.setOnClickListener {
            mProductClickListener.itemClick(position)
        }
    }

    class DashBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btn = itemView.findViewById<AppCompatButton>(R.id.btn_menus)!!
    }

    interface ProductClickListener {
        fun itemClick(position: Int)
    }
}