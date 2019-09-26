package com.expert.operrwork.view.oldDashboard

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.view.oldDashboard.myEmployeeProfile.MyEmployeeProfileFragment
import com.expert.operrwork.view.oldDashboard.timeSheet.TimeSheetFragment
import com.expert.operrwork.data.local.PreferenceManager
import com.expert.operrwork.util.Constant
import kotlinx.android.synthetic.main.bottom_sheet_time_punch.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlin.collections.ArrayList


/**
 * Created by Akshay.
 */
class DashboardOptionFragment : BaseFragment() {


    var mSideMenu = false
    var mDashboardAdapter: DashboardAdapter? = null
    var mData = ArrayList<String>()
    private var mBottomSheetBehaviour: BottomSheetBehavior<*>? = null
    var mPreferenceManager: PreferenceManager? = null
    var firstTime = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard_option, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreference()
    }

        override fun onStart() {
            super.onStart()
            if (mSideMenu) {
                (activity as AppCompatActivity).supportActionBar!!.title =resources.getString(R.string.tb_reset_pass_code)
            }
        }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun init() {
        mData.clear()
        resources.getStringArray(R.array.dashboard_option).toCollection(mData)
        setDashboardRecyclerView()
    }

    @SuppressLint("SimpleDateFormat")
    private fun bottomSide() {
        mBottomSheetBehaviour = BottomSheetBehavior.from(nestedScrollView)
        mBottomSheetBehaviour!!.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(p0: View, p1: Int) {
            }

            override fun onSlide(p0: View, p1: Float) {
            }
        })

        btn_punch_cancel.setOnClickListener {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        punch_date.text = currentDateWithWeekName(Constant.mPunchInDateFormat)
        punch_time.text = currentTime(Constant.mCommonTimeFormat)
    }

    //after getting data it update the Product in recyclerView
    private fun setDashboardRecyclerView() {
        val layoutManager = LinearLayoutManager(context!!)
        rv_menus.layoutManager = layoutManager
        mDashboardAdapter = DashboardAdapter(context!!, mData, object : DashboardAdapter.ProductClickListener {
            override fun itemClick(position: Int) {
                when {
                    mData[position].contentEquals(resources.getString(R.string.dashboard_overtime)) -> addReplaceFragment(
                        R.id.fl_container,
                        MyEmployeeProfileFragment(),
                        false,
                        true,
                        null
                    )
                    mData[position].contentEquals(resources.getString(R.string.dashboard_todo)) -> addReplaceFragment(
                        R.id.fl_container,
                        TimeSheetFragment(),
                        false,
                        true,
                        null
                    )
                }
            }
        })
        rv_menus.adapter = mDashboardAdapter
        mDashboardAdapter!!.notifyDataSetChanged()
    }


    fun initSharedPreference() {
        val prefs = activity!!.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)
        mPreferenceManager = PreferenceManager(prefs)
    }


}