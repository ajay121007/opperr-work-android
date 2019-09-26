package com.expert.operrwork.data.model.response.GetAllAdminTypeResponse

import com.google.gson.annotations.SerializedName

data class ListIpHourlyRestrictedItem(

	@field:SerializedName("timeEnd")
	val timeEnd: Any? = null,

	@field:SerializedName("dayOfWeek")
	val dayOfWeek: Int? = null,

	@field:SerializedName("timeStart")
	val timeStart: Any? = null,

	@field:SerializedName("ipAddress")
	val ipAddress: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("isChecked")
	val isChecked: Boolean? = null
)