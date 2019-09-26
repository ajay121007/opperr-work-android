package com.expert.operrwork.data.model.updatePlatformAdmin.response

import com.google.gson.annotations.SerializedName

class UpdatePlatformAdminResponse {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
