package com.expert.operrwork.data.model.response.getagencybyid
import com.google.gson.annotations.SerializedName
class GetAgencyByCompId {

    @SerializedName("data")
    var data: List<Datum>? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
