package com.expert.operrwork.data.model.companyAdminById

import com.google.gson.annotations.SerializedName

class GetCompanyAdminById {

    @SerializedName("data")
    var data: Data? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
