package com.expert.operrwork.data.model.group.addUpdateGroup.response
import com.google.gson.annotations.SerializedName


class AddUpdateGroupResponse {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
