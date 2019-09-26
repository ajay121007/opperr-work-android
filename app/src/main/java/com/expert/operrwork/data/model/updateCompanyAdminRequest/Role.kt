package com.expert.operrwork.data.model.updateCompanyAdminRequest

import com.google.gson.annotations.SerializedName

class Role {

    @SerializedName("createdAt")
    var createdAt: Long? = null
    @SerializedName("createdByUsr")
    var createdByUsr: Any? = null
    @SerializedName("defaultActionList")
    var defaultActionList: Any? = null
    @SerializedName("defaultVisibleList")
    var defaultVisibleList: Any? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("lastModifiedBy")
    var lastModifiedBy: Any? = null
    @SerializedName("level")
    var level: Long? = null
    @SerializedName("levelName")
    var levelName: String? = null
    @SerializedName("status")
    var status: Long? = null
    @SerializedName("updatedAt")
    var updatedAt: Long? = null

}
