package com.expert.operrwork.data.model.response.processMonitor

import com.google.gson.annotations.SerializedName
class ProcessMonitorResponse {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
