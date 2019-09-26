package com.expert.operrwork.data.model.response.allplatformadmin
import com.google.gson.annotations.SerializedName

class AllPlateFormAdmin {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
