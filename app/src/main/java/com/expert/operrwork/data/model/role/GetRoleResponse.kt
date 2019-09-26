package com.expert.operrwork.data.model.role

import com.google.gson.annotations.SerializedName
class GetRoleResponse {

    @SerializedName("data")
    var data: List<Datum>? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
