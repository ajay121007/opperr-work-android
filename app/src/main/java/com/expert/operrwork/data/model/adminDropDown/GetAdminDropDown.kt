package com.expert.operrwork.data.model.adminDropDown
import com.google.gson.annotations.SerializedName
class GetAdminDropDown {

    @SerializedName("data")
    var data: List<Datum>? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
