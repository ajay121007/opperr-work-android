package com.expert.operrwork.data.model.login.loginResponse
import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
