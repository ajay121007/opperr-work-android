package com.expert.operrwork.data.model.group.getAllGroup

import com.google.gson.annotations.SerializedName


data class GetAllGroupResponse(

    @field:SerializedName("data")
	val data: Data? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: String? = null
)