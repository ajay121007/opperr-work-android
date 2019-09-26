package com.expert.operrwork.data.model.createAdmin

import com.google.gson.annotations.SerializedName

class CreateAdminRequest {

    @SerializedName("actionList")
    var actionList: List<Long>? = null
    @SerializedName("agency")
    var agency: Agency? = null
    @SerializedName("company")
    var company: Company? = null
    @SerializedName("email")
    var email: String? = null

    @SerializedName("firstName")
    var firstName: String? = null

    @SerializedName("deviceAddress")
    var deviceAddress: String? = null

    @SerializedName("rememberToken")
    var rememberToken: String? = null


    @SerializedName("fcmToken")
    var fcmToken: String? = null

    @SerializedName("updatedAt")
    var updatedAt: Long? = null

    @SerializedName("middleName")
    var middleName: String? = null
    @SerializedName("gender")
    var gender: String? = null
    @SerializedName("lastName")
    var lastName: String? = null
    @SerializedName("listGroupIds")
    var listGroupIds: List<Long>? = null
    @SerializedName("menuList")
    var menuList: List<Long>? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("phone")
    var phone: String? = null
    @SerializedName("role")
    var role: Role? = null
    @SerializedName("secondaryPhone")
    var secondaryPhone: String? = null
    @SerializedName("status")
    var status: Long? = null
    @SerializedName("username")
    var username: String? = null

}
