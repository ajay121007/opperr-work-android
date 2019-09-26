package com.expert.operrwork.data.model.response.processMonitor
import com.google.gson.annotations.SerializedName

class Content {

    @SerializedName("agencyId")
    var agencyId: Long? = null
    @SerializedName("createdAt")
    var createdAt: Long? = null
    @SerializedName("createdByUsr")
    var createdByUsr: String? = null
    @SerializedName("details")
    var details: String? = null
    @SerializedName("duration")
    var duration: Long? = null
    @SerializedName("endTime")
    var endTime: Long? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("lastModifiedBy")
    var lastModifiedBy: String? = null
    @SerializedName("process")
    var process: String? = null
    @SerializedName("processStartTime")
    var processStartTime: Long? = null
    @SerializedName("requestStartTime")
    var requestStartTime: Long? = null
    @SerializedName("startedBy")
    var startedBy: Long? = null
    @SerializedName("startedByName")
    var startedByName: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("updatedAt")
    var updatedAt: Long? = null

}
