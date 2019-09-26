package com.expert.operrwork.data.model.response.getAllAgencyAdmin

import com.google.gson.annotations.SerializedName

class ListIpHourlyRestricted {

    @SerializedName("dayOfWeek")
    var dayOfWeek: Long? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("ipAddress")
    var ipAddress: String? = null
    @SerializedName("isChecked")
    var isChecked: Boolean? = null
    @SerializedName("timeEnd")
    var timeEnd: Long? = null
    @SerializedName("timeStart")
    var timeStart: Long? = null

}
