package com.expert.operrwork.data.model.response.GetAllAdminTypeResponse

import com.google.gson.annotations.SerializedName

data class Sort(

	@field:SerializedName("unsorted")
	val unsorted: Boolean? = null,

	@field:SerializedName("sorted")
	val sorted: Boolean? = null
)