package com.expert.operrwork.data.model.login.loginResponse
import com.google.gson.annotations.SerializedName
class Data {

    @SerializedName("apiKey")
    var apiKey: Any? = null
    @SerializedName("info")
    var info: Info? = null
    @SerializedName("setting")
    var setting: Any? = null
    @SerializedName("token")
    var token: String? = null

}
