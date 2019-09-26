package com.expert.operrwork.view.adminstrative.companyAdmin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.BR
import com.expert.operrwork.R
import com.expert.operrwork.data.model.response.companyAdmin.CompanyAdminResponse
import com.expert.operrwork.databinding.AdapterCompanyAdminsBinding

class CompanyAdminAdapter(
    list: MutableList<CompanyAdminResponse.ContentBean>,
    companyViewModel: CompanyViewModel, itemClick:ItemClick) :
    RecyclerView.Adapter<CompanyAdminAdapter.ViewHolder>() {

    var list: List<CompanyAdminResponse.ContentBean>?
    val itemClick:ItemClick
    val companyViewModel: CompanyViewModel

    init {
        this.list = list
        this.itemClick=itemClick
        this.companyViewModel=companyViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = DataBindingUtil.inflate<AdapterCompanyAdminsBinding>(inflater,
            R.layout.adapter_company_admins, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.companyData = list!!.get(position)
        holder.binding.setVariable(BR.companyViewModel, companyViewModel)
        holder.itemView.setOnClickListener {
            itemClick.onItemClick(position)
        }
    }

    fun customNotify(data: List<CompanyAdminResponse.ContentBean>) {
      this.list=data
        notifyDataSetChanged()
    }


    class ViewHolder(binding: AdapterCompanyAdminsBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: AdapterCompanyAdminsBinding

        init {
            this.binding = binding
        }
    }

    interface ItemClick{
       fun onItemClick(position: Int)
    }
}