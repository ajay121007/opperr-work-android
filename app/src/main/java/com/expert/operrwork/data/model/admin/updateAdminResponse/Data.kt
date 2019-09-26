package com.expert.operrwork.data.model.admin.updateAdminResponse

import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("actionList")
    var actionList: List<String>? = null
    @SerializedName("agency")
    var agency: Agency? = null
    @SerializedName("company")
    var company: Company? = null
    @SerializedName("companyId")
    var companyId: Any? = null
    @SerializedName("createdAt")
    var createdAt: Long? = null
    @SerializedName("createdByUsr")
    var createdByUsr: String? = null
    @SerializedName("deviceAddress")
    var deviceAddress: Any? = null
    @SerializedName("did")
    var did: Any? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("fcmToken")
    var fcmToken: Any? = null
    @SerializedName("firstName")
    var firstName: String? = null
    @SerializedName("gender")
    var gender: String? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("ip")
    var ip: Any? = null
    @SerializedName("isChangeStatusNotification")
    var isChangeStatusNotification: Boolean? = null
    @SerializedName("isEmailNotification")
    var isEmailNotification: Boolean? = null
    @SerializedName("isIpHourlyRestricted")
    var isIpHourlyRestricted: Boolean? = null
    @SerializedName("isIpRestricted")
    var isIpRestricted: Boolean? = null
    @SerializedName("isOverrideOTLimit")
    var isOverrideOTLimit: Boolean? = null
    @SerializedName("isSMSNotification")
    var isSMSNotification: Boolean? = null
    @SerializedName("lastModifiedBy")
    var lastModifiedBy: String? = null
    @SerializedName("lastName")
    var lastName: String? = null
    @SerializedName("lastPasswordChangedAt")
    var lastPasswordChangedAt: Long? = null
    @SerializedName("listGroupIds")
    var listGroupIds: List<Any>? = null
    @SerializedName("listIpHourlyRestricted")
    var listIpHourlyRestricted: List<Any>? = null
    @SerializedName("listIpRestricted")
    var listIpRestricted: List<Any>? = null
    @SerializedName("listSelectedEmailPositionId")
    var listSelectedEmailPositionId: List<Any>? = null
    @SerializedName("listSelectedSMSPositionId")
    var listSelectedSMSPositionId: List<Any>? = null
    @SerializedName("menuList")
    var menuList: List<String>? = null
    @SerializedName("middleName")
    var middleName: Any? = null
    @SerializedName("phone")
    var phone: String? = null
    @SerializedName("postAgencyLevel")
    var postAgencyLevel: Any? = null
    @SerializedName("postCompanyLevel")
    var postCompanyLevel: Any? = null
    @SerializedName("postDepartmentLevel")
    var postDepartmentLevel: Any? = null
    @SerializedName("profilePhoto")
    var profilePhoto: Any? = null
    @SerializedName("redirectPageId")
    var redirectPageId: Any? = null
    @SerializedName("rememberToken")
    var rememberToken: Any? = null
    @SerializedName("renewPasswordInterval")
    var renewPasswordInterval: Any? = null
    @SerializedName("role")
    var role: Role? = null
    @SerializedName("secondaryPhone")
    var secondaryPhone: String? = null
    @SerializedName("status")
    var status: Long? = null
    @SerializedName("updatedAt")
    var updatedAt: Long? = null
    @SerializedName("username")
    var username: String? = null

}
