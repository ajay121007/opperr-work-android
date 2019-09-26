package com.expert.operrwork.data.model.group.getAllGroup

import com.google.gson.annotations.SerializedName

data class Pageable(

	@field:SerializedName("paged")
	val paged: Boolean? = null,

	@field:SerializedName("pageNumber")
	val pageNumber: Int? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("pageSize")
	val pageSize: Int? = null,

	@field:SerializedName("unpaged")
	val unpaged: Boolean? = null,

	@field:SerializedName("sort")
	val sort: Sort? = null
)