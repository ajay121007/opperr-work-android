package com.expert.operrwork.data.model.group.getAllGroup

import com.google.gson.annotations.SerializedName

data class Role(

	@field:SerializedName("createdAt")
	val createdAt: Long? = null,

	@field:SerializedName("defaultActionList")
	val defaultActionList: Any? = null,

	@field:SerializedName("level")
	val level: Int? = null,

	@field:SerializedName("lastModifiedBy")
	val lastModifiedBy: Any? = null,

	@field:SerializedName("levelName")
	val levelName: String? = null,

	@field:SerializedName("createdByUsr")
	val createdByUsr: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("defaultVisibleList")
	val defaultVisibleList: Any? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Long? = null,

	@field:SerializedName("status")
	val status: Int? = null
)