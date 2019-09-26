package com.expert.operrwork.view.adminstrative.agencyAdmin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.BR
import com.expert.operrwork.R
import com.expert.operrwork.data.model.response.getAllAgencyAdmin.Content
import com.expert.operrwork.databinding.AdapterAgencyAdminsBinding

class AgencyAdminsAdapter(
    list: List<Content>?,
    adminstrativeViewModel: AgencyViewModel, itemClick:ItemClick) :
    RecyclerView.Adapter<AgencyAdminsAdapter.ViewHolder>() {

    var list: List<Content>?
    val itemClick:ItemClick
    var adminstrativeViewModel: AgencyViewModel

    init {
        this.list = list
        this.itemClick=itemClick
        this.adminstrativeViewModel=adminstrativeViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = DataBindingUtil.inflate<AdapterAgencyAdminsBinding>(inflater,
            R.layout.adapter_agency_admins, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = list!!.get(position)
        holder.binding.setVariable(BR.viewModel, adminstrativeViewModel)
        holder.itemView.setOnClickListener {
            itemClick.onItemClick(position)
        }
    }

    fun customNotify(data: List<Content>) {
      this.list=data
        notifyDataSetChanged()
    }


    class ViewHolder(binding: AdapterAgencyAdminsBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: AdapterAgencyAdminsBinding

        init {
            this.binding = binding
        }
    }

    interface ItemClick{
       fun onItemClick(position: Int)
    }
}