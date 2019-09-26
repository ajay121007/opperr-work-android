package com.expert.operrwork.data.model.group.addUpdateGroup.request

import com.google.gson.annotations.SerializedName

class AddUpdateGroupRequest {

    @SerializedName("actionList")
    var actionList: List<Long>? = null
    @SerializedName("adminGroups")
    var adminGroups: List<AdminGroup>? = null
    @SerializedName("menuList")
    var menuList: List<Long>? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("id")
    var id: String? = null

}
