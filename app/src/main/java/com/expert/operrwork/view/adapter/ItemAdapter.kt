package com.expert.operrwork.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import kotlinx.android.synthetic.main.adapter_items.view.*

class ItemAdapter(handler: ItemClickHandler, mCategoriesList: MutableList<String>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var list: MutableList<String>
    internal var handler: ItemClickHandler

    init {
        this.list = mCategoriesList
        this.handler = handler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_items,
            null
        )
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewItemName.setText(list.get(position))
        holder.itemView.setOnClickListener({
            handler.dropDownItemClick(position)
        })
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        init {


        }
    }

    interface ItemClickHandler {
        fun dropDownItemClick(position: Int)
    }

}