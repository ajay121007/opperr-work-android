package com.expert.operrwork.data.model.response.getAgencyAdminById
import com.google.gson.annotations.SerializedName

class GetAdminById {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
