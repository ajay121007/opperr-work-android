package com.expert.operrwork.data.model.response.getAllAgencyAdmin
import com.google.gson.annotations.SerializedName

class GetAllAgencyAdminResponse {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
