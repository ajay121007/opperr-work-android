package com.expert.operrwork.data.model.adminDropDown
import com.google.gson.annotations.SerializedName

class Datum {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("status")
    var status: String? = null
    @field:SerializedName("checked")
    var checked: Boolean? = false

}
