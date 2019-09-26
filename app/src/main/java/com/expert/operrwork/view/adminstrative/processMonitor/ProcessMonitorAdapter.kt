package com.expert.operrwork.view.adminstrative.processMonitor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.BR
import com.expert.operrwork.R
import com.expert.operrwork.data.model.response.processMonitor.Content
import com.expert.operrwork.databinding.AdapterPerformanceMonitorBinding

class ProcessMonitorAdapter(
    list: MutableList<Content>,
    processMonitorViewModel: ProcessMonitorViewModel, itemClick:ItemClick) :
    RecyclerView.Adapter<ProcessMonitorAdapter.ViewHolder>() {

    var list: List<Content>?
    val itemClick:ItemClick
    val processMonitorViewModel: ProcessMonitorViewModel

    init {
        this.list = list
        this.itemClick=itemClick
        this.processMonitorViewModel=processMonitorViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = DataBindingUtil.inflate<AdapterPerformanceMonitorBinding>(inflater,
            R.layout.adapter_performance_monitor, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = list!!.get(position)
        holder.binding.setVariable(BR.platformAdminViewModel, processMonitorViewModel)
        holder.itemView.setOnClickListener {
            itemClick.onItemClick(position)
        }
    }

    fun customNotify(data: List<Content>?) {
      this.list=data
        notifyDataSetChanged()
    }


    class ViewHolder(binding: AdapterPerformanceMonitorBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: AdapterPerformanceMonitorBinding

        init {
            this.binding = binding
        }
    }

    interface ItemClick{
       fun onItemClick(position: Int)
    }
}
