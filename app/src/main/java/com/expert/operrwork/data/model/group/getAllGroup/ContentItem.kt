package com.expert.operrwork.data.model.group.getAllGroup

import com.google.gson.annotations.SerializedName

data class ContentItem(

    @field:SerializedName("createdAt")
	val createdAt: Long? = null,

    @field:SerializedName("menuList")
	val menuList: List<String?>? = null,

    @field:SerializedName("createdBy")
	val createdBy: CreatedBy? = null,

    @field:SerializedName("lastModifiedBy")
	val lastModifiedBy: String? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("actionList")
	val actionList: List<String?>? = null,

    @field:SerializedName("createdByUsr")
	val createdByUsr: Any? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("updatedAt")
	val updatedAt: Long? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("checked")
	var checked: Boolean? = false
)