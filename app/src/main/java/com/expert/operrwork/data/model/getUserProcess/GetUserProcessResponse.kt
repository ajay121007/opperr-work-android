package com.expert.operrwork.data.model.getUserProcess
import com.google.gson.annotations.SerializedName

data class GetUserProcessResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)