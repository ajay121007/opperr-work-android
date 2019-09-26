package com.expert.operrwork.data.model.groupDetailById

import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("actionList")
    var actionList: List<String>? = null
    @SerializedName("createdAt")
    var createdAt: Long? = null
    @SerializedName("createdBy")
    var createdBy: CreatedBy? = null
    @SerializedName("createdByUsr")
    var createdByUsr: Any? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("lastModifiedBy")
    var lastModifiedBy: String? = null
    @SerializedName("menuList")
    var menuList: List<String>? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("updatedAt")
    var updatedAt: Long? = null

}
