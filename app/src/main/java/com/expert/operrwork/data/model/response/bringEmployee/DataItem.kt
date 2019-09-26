package com.expert.operrwork.data.model.response.bringEmployee

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("employeeDepartments")
	val employeeDepartments: Any? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("role")
	val role: Role? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("socialSecurityNumber")
	val socialSecurityNumber: String? = null,

	@field:SerializedName("companyName")
	val companyName: String? = null,

	@field:SerializedName("workHours")
	val workHours: List<WorkHoursItem?>? = null,

	@field:SerializedName("employeeStartDate")
	val employeeStartDate: Long? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: Long? = null,

	@field:SerializedName("matarialStatus")
	val matarialStatus: String? = null,

	@field:SerializedName("pin")
	val pin: String? = null,

	@field:SerializedName("punchStatus")
	val punchStatus: Any? = null,

	@field:SerializedName("rate")
	val rate: Int? = null,

	@field:SerializedName("rateFromDate")
	val rateFromDate: Long? = null,

	@field:SerializedName("facePersonId")
	val facePersonId: String? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("rateToDate")
	val rateToDate: Long? = null,

	@field:SerializedName("employeeDepartmentPositions")
	val employeeDepartmentPositions: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("department")
	val department: String? = null,

	@field:SerializedName("dependent")
	val dependent: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Long? = null,

	@field:SerializedName("menuList")
	val menuList: Any? = null,

	@field:SerializedName("weeklyHours")
	val weeklyHours: Int? = null,

	@field:SerializedName("salaryType")
	val salaryType: String? = null,

	@field:SerializedName("agency")
	val agency: Agency? = null,

	@field:SerializedName("weeklyWorkingTimeLimitation")
	val weeklyWorkingTimeLimitation: Any? = null,

	@field:SerializedName("lastModifiedBy")
	val lastModifiedBy: String? = null,

	@field:SerializedName("profile")
	val profile: Any? = null,

	@field:SerializedName("dateOfBirth")
	val dateOfBirth: Long? = null,

	@field:SerializedName("trackEmployeeMobile")
	val trackEmployeeMobile: Boolean? = null,

	@field:SerializedName("classification")
	val classification: String? = null,

	@field:SerializedName("sent")
	val sent: Boolean? = null,

	@field:SerializedName("weeklyWorkedHours")
	val weeklyWorkedHours: Any? = null,

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("employeeAdjustmentBonus")
	val employeeAdjustmentBonus: Any? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("checkInOutCounter")
	val checkInOutCounter: Int? = null,

	@field:SerializedName("createdByUsr")
	val createdByUsr: Any? = null,

	@field:SerializedName("position")
	val position: Any? = null,

	@field:SerializedName("rememberToken")
	val rememberToken: String? = null,

	@field:SerializedName("did")
	val did: Any? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)