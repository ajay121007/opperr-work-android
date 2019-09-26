package com.expert.operrwork.data.model.response.GetAllAdminTypeResponse

import com.google.gson.annotations.SerializedName

data class ListIpRestrictedItem(

	@field:SerializedName("ipAddress")
	val ipAddress: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("position")
	val position: Int? = null
)