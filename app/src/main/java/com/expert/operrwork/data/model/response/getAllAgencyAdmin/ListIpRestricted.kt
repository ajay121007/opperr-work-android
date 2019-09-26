package com.expert.operrwork.data.model.response.getAllAgencyAdmin
import com.google.gson.annotations.SerializedName

class ListIpRestricted {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("ipAddress")
    var ipAddress: String? = null
    @SerializedName("position")
    var position: Long? = null

}
