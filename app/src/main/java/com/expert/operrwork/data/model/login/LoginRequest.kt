package com.expert.operrwork.data.model.login
import com.google.gson.annotations.SerializedName

class LoginRequest {

    @SerializedName("password")
    var password: String? = null
    @SerializedName("username")
    var username: String? = null

}
