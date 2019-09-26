package com.expert.operrwork.data.model.group.getAllGroup

import com.google.gson.annotations.SerializedName


data class Sort(

	@field:SerializedName("unsorted")
	val unsorted: Boolean? = null,

	@field:SerializedName("sorted")
	val sorted: Boolean? = null
)