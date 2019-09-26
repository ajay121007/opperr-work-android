package com.expert.operrwork.data.model.response.GetAllAdminTypeResponse

import com.google.gson.annotations.SerializedName

data class GetAllAdminResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)