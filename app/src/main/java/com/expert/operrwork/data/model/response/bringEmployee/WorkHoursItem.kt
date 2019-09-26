package com.expert.operrwork.data.model.response.bringEmployee

import com.google.gson.annotations.SerializedName

data class WorkHoursItem(

	@field:SerializedName("createdAt")
	val createdAt: Any? = null,

	@field:SerializedName("dayOfWeek")
	val dayOfWeek: Int? = null,

	@field:SerializedName("lastModifiedBy")
	val lastModifiedBy: String? = null,

	@field:SerializedName("startTime")
	val startTime: Int? = null,

	@field:SerializedName("createdByUsr")
	val createdByUsr: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("endTime")
	val endTime: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Any? = null
)