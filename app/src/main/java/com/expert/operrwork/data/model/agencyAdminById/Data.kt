package com.expert.operrwork.data.model.agencyAdminById

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("role")
	val role: Role? = null,

	@field:SerializedName("isIpHourlyRestricted")
	val isIpHourlyRestricted: Boolean? = null,

	@field:SerializedName("renewPasswordInterval")
	val renewPasswordInterval: Any? = null,

	@field:SerializedName("isChangeStatusNotification")
	val isChangeStatusNotification: Boolean? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("lastPasswordChangedAt")
	val lastPasswordChangedAt: Any? = null,

	@field:SerializedName("redirectPageId")
	val redirectPageId: Any? = null,

	@field:SerializedName("createdAt")
	val createdAt: Long? = null,

	@field:SerializedName("deviceAddress")
	val deviceAddress: Any? = null,

	@field:SerializedName("profilePhoto")
	val profilePhoto: Any? = null,

	@field:SerializedName("listIpHourlyRestricted")
	val listIpHourlyRestricted: List<Any?>? = null,

	@field:SerializedName("isSMSNotification")
	val isSMSNotification: Boolean? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("fcmToken")
	val fcmToken: String? = null,

	@field:SerializedName("listIpRestricted")
	val listIpRestricted: List<Any?>? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Long? = null,

	@field:SerializedName("listSelectedEmailPositionId")
	val listSelectedEmailPositionId: List<Any?>? = null,

	@field:SerializedName("menuList")
	val menuList: List<String?>? = null,

	@field:SerializedName("agency")
	val agency: Agency? = null,

	@field:SerializedName("isIpRestricted")
	val isIpRestricted: Boolean? = null,

	@field:SerializedName("lastModifiedBy")
	val lastModifiedBy: String? = null,

	@field:SerializedName("ip")
	val ip: Any? = null,

	@field:SerializedName("postDepartmentLevel")
	val postDepartmentLevel: Any? = null,

	@field:SerializedName("actionList")
	val actionList: Any? = null,

	@field:SerializedName("isEmailNotification")
	val isEmailNotification: Boolean? = null,

	@field:SerializedName("postCompanyLevel")
	val postCompanyLevel: Any? = null,

	@field:SerializedName("listGroupIds")
	val listGroupIds: List<Int?>? = null,

	@field:SerializedName("listSelectedSMSPositionId")
	val listSelectedSMSPositionId: List<Any?>? = null,

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("companyId")
	val companyId: Any? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("middleName")
	val middleName: String? = null,

	@field:SerializedName("createdByUsr")
	val createdByUsr: Any? = null,

	@field:SerializedName("postAgencyLevel")
	val postAgencyLevel: Any? = null,

	@field:SerializedName("rememberToken")
	val rememberToken: String? = null,

	@field:SerializedName("isOverrideOTLimit")
	val isOverrideOTLimit: Boolean? = null,

	@field:SerializedName("secondaryPhone")
	val secondaryPhone: String? = null,

	@field:SerializedName("did")
	val did: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)