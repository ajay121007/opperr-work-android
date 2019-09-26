package com.expert.operrwork.view.oldDashboard

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.view.oldDashboard.myEmployeeProfile.MyEmployeeProfileFragment
import com.expert.operrwork.view.oldDashboard.payment.PayStubsFragment
import com.expert.operrwork.view.oldDashboard.requestOffTime.RequestOffDaysFragment
import com.expert.operrwork.view.oldDashboard.timeSheet.TimeSheetFragment
import com.expert.operrwork.data.local.PreferenceManager
import com.expert.operrwork.view.userAccess.registration.RegisterEmployeeFragment
import com.expert.operrwork.util.Constant
import kotlinx.android.synthetic.main.bottom_sheet_time_punch.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.security.MessageDigest
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Akshay.
 */
class OldDashboardFragment : BaseFragment() {

    var mDashboardAdapter: DashboardAdapter? = null
    var mData = ArrayList<String>()
    private var mBottomSheetBehaviour: BottomSheetBehavior<*>? = null
    var itemDetailsViewModel: DayTimeViewModel? = null
    var mPreferenceManager: PreferenceManager? = null
    var firstTime = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreference()
        itemDetailsViewModel = ViewModelProviders.of(this).get(DayTimeViewModel::class.java)
        initObserver()
    }

    private fun initObserver() {
        //timer update the Data
        itemDetailsViewModel!!.getCountDown().observe(this, androidx.lifecycle.Observer {
            Log.d(Constant.Tag, it.days)
            Log.d(Constant.Tag, it.time)
        })
    }

    @SuppressLint("InlinedApi", "ResourceAsColor")
    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.tb_hello_username)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        regScreen()
    }

    private fun regScreen() {
        firstTime = mPreferenceManager!!.getValue(Constant.sharedUserUpdateFirstTime, false)
        if (!firstTime) {
            addReplaceFragment(R.id.fl_container,
                RegisterEmployeeFragment(), false, true, null)
            mPreferenceManager!!.setValue(Constant.sharedUserUpdateFirstTime, true)
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun init() {
        mData.clear()
        resources.getStringArray(R.array.dashboard).toCollection(mData)
        setDashboardRecyclerView()
        txt_current_day.text = currentDateWithWeekName(Constant.mDashboardDateFormat)
        bottomSide()
        Log.d(Constant.Tag, toMD5Hash("Aa123456@").toLowerCase())
        Log.d(Constant.Tag, milliseconds("19-05-2019 02:00:00").toString())
        itemDetailsViewModel!!.countDownRun(milliseconds("19-05-2019 02:00:00"))
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

        btn_punch_in.setOnClickListener {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
            dialogAddNotes()
        }

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
                    mData[position].contentEquals(resources.getString(R.string.dashboard_profile)) -> addReplaceFragment(
                        R.id.fl_container,
                        MyEmployeeProfileFragment(),
                        false,
                        true,
                        null
                    )
                    mData[position].contentEquals(resources.getString(R.string.dashboard_clock)) -> mBottomSheetBehaviour!!.state =
                        BottomSheetBehavior.STATE_EXPANDED
                    mData[position].contentEquals(resources.getString(R.string.dashboard_time_sheet)) -> addReplaceFragment(
                        R.id.fl_container,
                        TimeSheetFragment(),
                        false,
                        true,
                        null
                    )
                    mData[position].contentEquals(resources.getString(R.string.dashboard_pay)) -> addReplaceFragment(
                        R.id.fl_container,
                        PayStubsFragment(),
                        false,
                        true,
                        null
                    )
                    mData[position].contentEquals(resources.getString(R.string.dashboard_request)) -> addReplaceFragment(
                        R.id.fl_container,
                        RequestOffDaysFragment(),
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

    //Open Add Note Dialog
    private fun dialogAddNotes() {
        val dialogs = Dialog(context!!, R.style.AppThemeUser)
        dialogs.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialogs.window!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.transparentBlack)))
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(false)
        dialogs.setContentView(R.layout.dialog_clock_in)

        val btnAddNote = dialogs.findViewById(R.id.btn_clock_in_add_note) as AppCompatButton
        val btnCancel = dialogs.findViewById(R.id.btn_clock_in_done) as AppCompatButton

        btnAddNote.setOnClickListener {
            dialogs.dismiss()
            addReplaceFragment(R.id.fl_container, PunchInAddNoteFragment(), false, true, null)
        }

        btnCancel.setOnClickListener {
            dialogs.dismiss()
        }

        dialogs.show()
    }

    private fun toMD5Hash(text: String): String {
        var result = ""
        try {
            val md5 = MessageDigest.getInstance("MD5")
            val md5HashBytes = md5.digest(text.toByteArray()).toTypedArray()
            result = byteArrayToHexString(md5HashBytes)
        } catch (e: Exception) {
            result = "error: ${e.message}"
        }
        return result
    }

    private fun byteArrayToHexString(array: Array<Byte>): String {
        val result = StringBuilder(array.size * 2)
        for (byte in array) {
            val toAppend = String.format("%2X", byte).replace(" ", "0") // hexadecimal
            result.append(toAppend)
        }
        //        result.setLength(result.length - 1) // remove last '-'
        return result.toString()
    }

    private fun milliseconds(date: String): Long {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ROOT)
        try {
            val mDate = sdf.parse(date)
            val timeInMilliseconds = mDate.time
            println("Date in milli :: $timeInMilliseconds")
            return timeInMilliseconds
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    fun initSharedPreference() {
        val prefs = activity!!.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)
        mPreferenceManager = PreferenceManager(prefs)
    }


}