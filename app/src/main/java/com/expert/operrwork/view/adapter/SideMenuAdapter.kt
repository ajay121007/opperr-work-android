package com.expert.operrwork.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.util.Constant

/**
 * Created by Akshay.
 * seller product list adapter
 * @property mProductClickListener click Listener
 */
@Suppress("UNREACHABLE_CODE")
class SideMenuAdapter(val mContext: Context, var mList: List<String>, var mProductClickListener: ProductClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!mList[position].equals("NEWS", true)) {
            val mDayHolder = (holder as DashBoardSideMenuViewHolder)
            val sideMenuName = mList[position]
            mDayHolder.mSideMenu.text = sideMenuName
            mDayHolder.itemView.setOnClickListener {
                mProductClickListener.itemClick(position)
            }
        } else {
            val mNewsHolder = (holder as NewsViewHolder)
            val name = mList[position]
            mNewsHolder.mSideMenu.text = name
            mNewsHolder.itemView.setOnClickListener {
                mProductClickListener.itemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(Constant.Tag, "-->$viewType")
        return when (viewType) {
            0 -> {
                val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_side_menu, parent, false)
                return DashBoardSideMenuViewHolder(
                    viewHolder
                )
            }
            1 -> {
                val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_side_menu_news, parent, false)
                return NewsViewHolder(
                    viewHolder
                )
            }
            else -> null!!
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (!mList[position].equals("NEWS", true)) {
            0
        } else {
            1
        }
    }

    class DashBoardSideMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mSideMenu = itemView.findViewById<AppCompatTextView>(R.id.txt_side_menu)!!
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mSideMenu = itemView.findViewById<AppCompatTextView>(R.id.txt_side_menu_news)!!
    }

    interface ProductClickListener {
        fun itemClick(position: Int)
    }
}