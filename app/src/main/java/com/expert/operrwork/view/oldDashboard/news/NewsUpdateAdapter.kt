package com.expert.operrwork.view.oldDashboard.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.data.model.demo.News

/**
 * Created by Akshay.
 * seller product list adapter
 * @property mProductClickListener click Listener
 */
class NewsUpdateAdapter(val mContext: Context, var mList: ArrayList<News>, var mProductClickListener: ProductClickListener) : RecyclerView.Adapter<NewsUpdateAdapter.DashBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_updates_news, parent, false)
        return DashBoardViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val news = mList[position]
        holder.mDate.text = news.date
        holder.mFrom.text = news.from
        holder.mTopic.text = news.topic
        holder.itemView.setOnClickListener {
            mProductClickListener.itemClick(position)
        }
    }

    class DashBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mDate = itemView.findViewById<AppCompatTextView>(R.id.txt_date)!!
        val mFrom = itemView.findViewById<AppCompatTextView>(R.id.txt_From_Name)!!
        val mTopic = itemView.findViewById<AppCompatTextView>(R.id.txt_Topic)!!
    }

    interface ProductClickListener {
        fun itemClick(position: Int)
    }
}