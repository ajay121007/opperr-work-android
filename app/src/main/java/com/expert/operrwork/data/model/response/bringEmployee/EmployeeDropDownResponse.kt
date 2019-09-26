package com.expert.operrwork.data.model.response.bringEmployee

import com.google.gson.annotations.SerializedName

data class EmployeeDropDownResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)