package com.expert.operrwork.view.adminstrative.platformadmin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.BR
import com.expert.operrwork.R
import com.expert.operrwork.data.model.response.allplatformadmin.Content
import com.expert.operrwork.databinding.AdapterPlateformAdminBinding
import kotlinx.android.synthetic.main.adapter_plateform_admin.view.*

class PlatformAdminAdapter(
    list: List<Content>?,
    platformAdminViewModel: PlatformAdminViewModel, itemClick:ItemClick) :
    RecyclerView.Adapter<PlatformAdminAdapter.ViewHolder>() {

    var list: List<Content>?
    val itemClick:ItemClick
    val platformAdminViewModel: PlatformAdminViewModel

    init {
        this.list = list
        this.itemClick=itemClick
        this.platformAdminViewModel=platformAdminViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = DataBindingUtil.inflate<AdapterPlateformAdminBinding>(inflater,
            R.layout.adapter_plateform_admin, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = list!!.get(position)
        holder.binding.setVariable(BR.platformAdminViewModel, platformAdminViewModel)
        holder.itemView.imageButtonEdit.setOnClickListener {
            itemClick.onItemClick(position)
        }
    }

    fun customNotify(data: List<Content>?) {
      this.list=data
        notifyDataSetChanged()
    }


    class ViewHolder(binding: AdapterPlateformAdminBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: AdapterPlateformAdminBinding

        init {
            this.binding = binding
        }
    }

    interface ItemClick{
       fun onItemClick(position: Int)
    }
}