package com.expert.operrwork.data.model.response.getallcompany

import com.google.gson.annotations.SerializedName

class Datum {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("status")
    var status: Long? = null

}
