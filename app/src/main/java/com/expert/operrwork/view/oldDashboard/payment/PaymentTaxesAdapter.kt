package com.expert.operrwork.view.oldDashboard.payment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.data.model.demo.PayStubsTaxesPayment

/**
 * Created by Akshay.
 * seller product list adapter
 * @property mItemClickListener click Listener
 */
class PaymentTaxesAdapter(val mContext: Context, var mList: List<PayStubsTaxesPayment>, var mItemClickListener: ItemClickListener) : RecyclerView.Adapter<PaymentTaxesAdapter.DashBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_pay_stubs_taxes_details, parent, false)
        return DashBoardViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        holder.txtTaxesType.text = mList[position].mType
        holder.txtTaxesCurrent.text = mList[position].mCurrent
        holder.txtTaxesYTD.text = mList[position].mYTD
        holder.itemView.setOnClickListener {
            mItemClickListener.itemClick(position)
        }
    }

    class DashBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTaxesType = itemView.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_taxes_details_type)!!
        val txtTaxesCurrent = itemView.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_taxes_details_current)!!
        val txtTaxesYTD = itemView.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_taxes_details_ytd)!!
    }

    interface ItemClickListener {
        fun itemClick(position: Int)
    }
}