package com.expert.operrwork.data.model.forgotPassword
import com.google.gson.annotations.SerializedName

class ForgotPasswordRequest {

    @SerializedName("email")
    var email: String? = null
    @SerializedName("type")
    var type: String? = null

}
