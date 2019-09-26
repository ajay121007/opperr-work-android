package com.expert.operrwork.data.model.login.loginResponse
import com.google.gson.annotations.SerializedName
class Info {

    @SerializedName("actionList")
    var actionList: List<String>? = null
    @SerializedName("agency")
    var agency: Any? = null
    @SerializedName("company")
    var company: Any? = null
    @SerializedName("companyId")
    var companyId: Any? = null
    @SerializedName("createdAt")
    var createdAt: Long? = null
    @SerializedName("createdByUsr")
    var createdByUsr: Any? = null
    @SerializedName("deviceAddress")
    var deviceAddress: String? = null
    @SerializedName("did")
    var did: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("fcmToken")
    var fcmToken: String? = null
    @SerializedName("firstName")
    var firstName: String? = null
    @SerializedName("gender")
    var gender: String? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("ip")
    var ip: String? = null
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
    var lastPasswordChangedAt: Any? = null
    @SerializedName("listGroupIds")
    var listGroupIds: List<Any>? = null
    @SerializedName("listIpHourlyRestricted")
    var listIpHourlyRestricted: List<Any>? = null
    @SerializedName("listIpRestricted")
    var listIpRestricted: List<Any>? = null
    @SerializedName("listSelectedEmailPositionId")
    var listSelectedEmailPositionId: Any? = null
    @SerializedName("listSelectedSMSPositionId")
    var listSelectedSMSPositionId: Any? = null
    @SerializedName("menuList")
    var menuList: List<String>? = null
    @SerializedName("middleName")
    var middleName: String? = null
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
    var rememberToken: String? = null
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
