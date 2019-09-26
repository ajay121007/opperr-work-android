package com.expert.operrwork.data.model.updatePlatformAdmin.request

import com.google.gson.annotations.SerializedName

class UpdatePlatformAdminRequest {

    @SerializedName("updatedAt")
    var updatedAt: Long? = null
    @SerializedName("rememberToken")
    var rememberToken: String? = null
    @SerializedName("fcmToken")
    var fcmToken: String? = null
    @SerializedName("createdAt")
    var createdAt: Long? = null
    @SerializedName("createdByUsr")
    var createdByUsr: Any? = null
    @SerializedName("actionList")
    var actionList: List<Long>? = null
    @SerializedName("address")
    var address: String? = null
    @SerializedName("did")
    var did: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("firstName")
    var firstName: String? = null
    @SerializedName("gender")
    var gender: String? = null
    @SerializedName("lastName")
    var lastName: String? = null
    @SerializedName("menuList")
    var menuList: List<Long>? = null
    @SerializedName("middleName")
    var middleName: String? = null
    @SerializedName("phone")
    var phone: String? = null
    @SerializedName("role")
    var role: Role? = null
    @SerializedName("deviceAddress")
    var deviceAddress: String? = null
    @SerializedName("isChangeStatusNotification")
    var isChangeStatusNotification: Boolean? = null
    @SerializedName("secondaryPhone")
    var secondaryPhone: String? = null
    @SerializedName("status")
    var status: Long? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("username")
    var username: String? = null
   @SerializedName("profilePhoto")
    var profilePhoto: String? = null

}
