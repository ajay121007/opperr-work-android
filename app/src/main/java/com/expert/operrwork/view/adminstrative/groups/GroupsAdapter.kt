package com.expert.operrwork.view.adminstrative.groups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.BR
import com.expert.operrwork.R
import com.expert.operrwork.data.model.group.getAllGroup.ContentItem
import com.expert.operrwork.databinding.AdapterGroupsBinding

class GroupsAdapter(
    list: List<ContentItem?>?,
    groupViewModel: GroupViewModel, itemClick:ItemClick) :
    RecyclerView.Adapter<GroupsAdapter.ViewHolder>() {

    var list: List<ContentItem?>?
    val itemClick:ItemClick
    val groupViewModel: GroupViewModel

    init {
        this.list = list
        this.itemClick=itemClick
        this.groupViewModel=groupViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = DataBindingUtil.inflate<AdapterGroupsBinding>(inflater,
            R.layout.adapter_groups, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = list!!.get(position)
        holder.binding.setVariable(BR.groupViewModel, groupViewModel)
        holder.itemView.setOnClickListener {
            itemClick.onItemClick(position)
        }
    }



    class ViewHolder(binding: AdapterGroupsBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: AdapterGroupsBinding

        init {
            this.binding = binding
        }
    }

    interface ItemClick{
       fun onItemClick(position: Int)
    }
}