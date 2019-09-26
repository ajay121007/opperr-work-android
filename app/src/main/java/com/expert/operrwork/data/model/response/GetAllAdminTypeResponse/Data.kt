package com.expert.operrwork.data.model.response.GetAllAdminTypeResponse

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("last")
	val last: Boolean? = null,

	@field:SerializedName("numberOfElements")
	val numberOfElements: Int? = null,

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("totalPages")
	val totalPages: Int? = null,

	@field:SerializedName("pageable")
	val pageable: Pageable? = null,

	@field:SerializedName("sort")
	val sort: Sort? = null,

	@field:SerializedName("content")
	val content: List<ContentItem?>? = null,

	@field:SerializedName("first")
	val first: Boolean? = null,

	@field:SerializedName("totalElements")
	val totalElements: Int? = null
)