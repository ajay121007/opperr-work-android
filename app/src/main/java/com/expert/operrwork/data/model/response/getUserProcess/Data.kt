package com.expert.operrwork.data.model.response.getUserProcess
import com.google.gson.annotations.SerializedName


data class Data(

	@field:SerializedName("process")
	val process: String? = null,

	@field:SerializedName("startedBy")
	val startedBy: Int? = null,

	@field:SerializedName("lastModifiedBy")
	val lastModifiedBy: String? = null,

	@field:SerializedName("agencyId")
	val agencyId: Int? = null,

	@field:SerializedName("startedByName")
	val startedByName: String? = null,

	@field:SerializedName("requestStartTime")
	val requestStartTime: Long? = null,

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: Long? = null,

	@field:SerializedName("processStartTime")
	val processStartTime: Long? = null,

	@field:SerializedName("details")
	val details: String? = null,

	@field:SerializedName("createdByUsr")
	val createdByUsr: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("endTime")
	val endTime: Long? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Long? = null,

	@field:SerializedName("status")
	val status: String? = null
)