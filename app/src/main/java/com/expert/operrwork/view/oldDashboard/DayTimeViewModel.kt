package com.expert.operrwork.view.oldDashboard

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.expert.operrwork.base.BaseViewModel
import com.expert.operrwork.data.model.response.CountDownTimers
import com.expert.operrwork.util.Constant
import java.text.SimpleDateFormat

/**
 * Created by Akshay.
 * run the timer
 */
class DayTimeViewModel : BaseViewModel() {
    private val mDelay = 1000
    private val mHandler = Handler(Looper.getMainLooper())
    private var timeData = MutableLiveData<CountDownTimers>()
    /**
     * It's run the handler and set the value in live data
     * @param EndDate getting the endDate
     */
    @SuppressLint("SimpleDateFormat")
    fun countDownRun(EndDate: Long?) {
        mHandler.removeCallbacksAndMessages(null)
        mHandler.postDelayed({
            val outputTime = Math.abs(EndDate!! - System.currentTimeMillis())
            Log.d(Constant.Tag, "EndDate -->$EndDate")
            Log.d(Constant.Tag, "Output -->$outputTime")
            Log.d(Constant.Tag, "SystemDate -->${System.currentTimeMillis()}")
            val date = java.util.Date(outputTime)
            val day = SimpleDateFormat("dd").format(date)
            val time = SimpleDateFormat("hh:mm:ss").format(date)
            val days = if (day.toInt() <= 1) "$day Day" else "$day Days"
            timeData.value = CountDownTimers(days, time)
            countDownRun(EndDate)
        }, mDelay.toLong())
    }

    fun getCountDown(): MutableLiveData<CountDownTimers> {
        return timeData
    }
}