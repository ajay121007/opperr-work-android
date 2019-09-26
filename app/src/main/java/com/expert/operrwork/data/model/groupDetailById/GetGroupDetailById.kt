package com.expert.operrwork.data.model.groupDetailById

import com.google.gson.annotations.SerializedName


class GetGroupDetailById {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
