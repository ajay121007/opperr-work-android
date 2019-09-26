package com.expert.operrwork.data.model.response.bringEmployee

import com.google.gson.annotations.SerializedName


data class Profile(

	@field:SerializedName("fileClass")
	val fileClass: String? = null,

	@field:SerializedName("fileName")
	val fileName: String? = null,

	@field:SerializedName("lastModifiedBy")
	val lastModifiedBy: String? = null,

	@field:SerializedName("filePath")
	val filePath: String? = null,

	@field:SerializedName("entityId")
	val entityId: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: Long? = null,

	@field:SerializedName("fileSize")
	val fileSize: Int? = null,

	@field:SerializedName("fileUrl")
	val fileUrl: String? = null,

	@field:SerializedName("createdByUsr")
	val createdByUsr: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("entity")
	val entity: String? = null,

	@field:SerializedName("fileType")
	val fileType: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Long? = null,

	@field:SerializedName("status")
	val status: Int? = null
)