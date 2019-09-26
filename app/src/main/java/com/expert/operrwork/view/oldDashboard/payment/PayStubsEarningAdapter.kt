package com.expert.operrwork.view.oldDashboard.payment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.data.model.demo.PayStubsPayment
import com.expert.operrwork.util.Constant


/**
 * Created by Akshay.
 */
@Suppress("UNREACHABLE_CODE")
class PayStubsEarningAdapter(private val mContext: Context, private val mArrayList: ArrayList<PayStubsPayment>, var itemClick: ItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        Log.d(Constant.Tag, "-->$viewType")
        return when (viewType) {
            0 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_pay_stubs_earnings_details, parent, false)
                return PaymentEarningViewHolder(view)
            }
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_pay_stubs_earnings_details_total, parent, false)
                return PaymentEarningTotalViewHolder(view)
            }
            else -> null!!
        }
    }


    override fun getItemCount(): Int {
        return mArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mArrayList[position].mViewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (mArrayList[position].mViewType) {
            0 -> {
                val mDayHolder = (holder as PaymentEarningViewHolder)
                mDayHolder.bind(position)
                mDayHolder.mEarningType.text = mArrayList[position].mType
                mDayHolder.mEarningHr.text = mArrayList[position].mHr
                mDayHolder.mEarningHrs.text = mArrayList[position].mHrs
                mDayHolder.mEarningCurrent.text = mArrayList[position].mCurrent
                mDayHolder.mEarningYtd.text = mArrayList[position].mYTD
            }
            1 -> {
                val mTimeHolder = (holder as PaymentEarningTotalViewHolder)
                mTimeHolder.bind(position)
                mTimeHolder.mTotal.text = mArrayList[position].mType
                mTimeHolder.mTotalCurrent.text = mArrayList[position].mCurrent
                mTimeHolder.mTotalYTD.text = mArrayList[position].mYTD
            }
            else -> null!!
        }
    }

    class PaymentEarningViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mEarningType = view.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_earning_details_type)!!
        val mEarningHr = view.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_earning_details_hr)!!
        val mEarningHrs = view.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_earning_details_hrs)!!
        val mEarningCurrent= view.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_earning_details_current)!!
        val mEarningYtd= view.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_earning_details_ytd)!!
        fun bind(position: Int) {
        }
    }

    class PaymentEarningTotalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mTotal = view.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_earning_details_total)!!
        val mTotalCurrent = view.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_earning_details_total_current)!!
        val mTotalYTD = view.findViewById<AppCompatTextView>(R.id.txt_pay_stubs_earning_details_total_ytd)!!
        fun bind(position: Int) {
        }
    }

    interface ItemClick {
        fun buttonClick(Position: Int)
    }
}