package com.expert.operrwork.data.model.response.getallcompany

import com.google.gson.annotations.SerializedName

class GetAllCompanyDropDownResponse {

    @SerializedName("data")
    var data: List<Datum>? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("status")
    var status: String? = null

}
