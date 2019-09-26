package com.expert.operrwork.util.helper

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat

class DateUtils {

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getConvertDate(GetDate: String): String? {
            var formattedDate: String? = null
            try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val outputFormat = SimpleDateFormat("dd MMM yyyy")
                val date = inputFormat.parse(GetDate)
                formattedDate = outputFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return formattedDate
        }
    }


    /**
     * It's run the handler and set the value in live data
     */
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun BidDateFormat(EndDate: Long?):String {
        val date = java.util.Date(EndDate!!)
        return SimpleDateFormat("MMM dd,YYYY hh:mm").format(date)
    }

}