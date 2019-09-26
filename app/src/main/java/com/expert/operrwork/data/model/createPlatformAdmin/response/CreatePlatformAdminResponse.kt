package com.expert.operrwork.data.model.createPlatformAdmin.response

import com.google.gson.annotations.SerializedName

class CreatePlatformAdminResponse {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
