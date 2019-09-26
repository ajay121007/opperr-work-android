package com.expert.operrwork.util

/**
 * Created by Akshay.
 */
class Constant {
    companion object {

        val SharedPrefName="OperrWork"
        val TOKEN="token"
        val IS_LOGIN ="login"
        const val Tag = "Tag"
        var httpCacheDirectory: String = "http-cache"
        var cacheControl: String = "Cache-Control"
        const val mDashboardDateFormat = "EEEE MMMM dd, YYYY"
        const val mPunchInDateFormat = "EEEE MM/dd/YYYY"
        const val mDateOnlyDateFormat = "MM/dd/YYYY"
        const val mCommonTimeFormat = "hh:mm a"
        const val mTermsAndConditions = "https://policies.google.com/terms"
        val DemoTimeArray = arrayListOf("9.00AM", "12.00PM", "3.00PM", "6.00PM", "10.00PM", "12.00AM")
        val DemoWeekArray = arrayListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val DemoInOutArray = arrayListOf("In", "Out", "In", "Out", "In", "Out")
        val DemoTypes =
            arrayListOf("Regular", "O.T-1.5", "SubTotal", "Holiday", "MINS ER", "Total Wages", "Total", "NetPay")
        val DemoTaxesTypes = arrayListOf("MCWH", "SSWH", "FITW")
        val DemoLeaveType = arrayListOf("Sick leave", "Vacation", "Holiday")
        val DemoLeaveDays = arrayListOf("3 days", "5 days", "2 days")
        val DemoHistoryDays = arrayListOf(
            "01/05/19 - 02/06/19",
            "02/06/19 - 03/07/19",
            "03/07/19 - 04/08/19",
            "04/08/19 - 05/09/19",
            "05/09/19 - 06/10/19 ",
            "06/10/19 - 07/11/19",
            "07/11/19 - 08/12/19"
        )
        val DemoHr = arrayListOf("0.00", "0.00", "", "", "", "", "", "")
        val DemoHrs = arrayListOf("40:00", "0.00", "40:00", "", "", "", "", "")
        val DemoCurrent = arrayListOf("0 00", "0 00", "0 00", "0 00", "0 00", "0 00", "0 00", "0 00")
        val topic = arrayListOf(
            "Building Rules Regarding",
            "Electronic Service Problem",
            "Marketing Conference",
            "Building Rules Regarding",
            "Electronic Service Problem",
            "Marketing Conference"
        )


        var baseURL: String = "http://69.18.218.57:8080/"
        var sharedUserUpdateFirstTime = "userUpdateFirstTime"
    }
}