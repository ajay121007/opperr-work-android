package com.expert.operrwork.data.model.response.bringEmployee

import com.google.gson.annotations.SerializedName

data class Agency(

	@field:SerializedName("licenseNo")
	val licenseNo: String? = null,

	@field:SerializedName("offDaysMultiple")
	val offDaysMultiple: Any? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("trackingMode")
	val trackingMode: Any? = null,

	@field:SerializedName("contactPerson")
	val contactPerson: String? = null,

	@field:SerializedName("holiday")
	val holiday: String? = null,

	@field:SerializedName("minimumWage")
	val minimumWage: Any? = null,

	@field:SerializedName("agreementSignature")
	val agreementSignature: Any? = null,

	@field:SerializedName("createdAt")
	val createdAt: Long? = null,

	@field:SerializedName("addressTwo")
	val addressTwo: Any? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("fax")
	val fax: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Long? = null,

	@field:SerializedName("upperLeftLabel")
	val upperLeftLabel: Any? = null,

	@field:SerializedName("lastModifiedBy")
	val lastModifiedBy: String? = null,

	@field:SerializedName("is2ndVerificationEnable")
	val is2ndVerificationEnable: Boolean? = null,

	@field:SerializedName("zipcode")
	val zipcode: String? = null,

	@field:SerializedName("capacityNum")
	val capacityNum: Any? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("createdBy")
	val createdBy: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mainWhiteLabel")
	val mainWhiteLabel: Any? = null,

	@field:SerializedName("createdByUsr")
	val createdByUsr: Any? = null,

	@field:SerializedName("addressOne")
	val addressOne: String? = null,

	@field:SerializedName("agencyType")
	val agencyType: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("cctvUrl")
	val cctvUrl: Any? = null,

	@field:SerializedName("meals")
	val meals: Any? = null
)