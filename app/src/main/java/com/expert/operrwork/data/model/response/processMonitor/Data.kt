package com.expert.operrwork.data.model.response.processMonitor
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("content")
    var content: List<Content>? = null
    @SerializedName("first")
    var first: Boolean? = null
    @SerializedName("last")
    var last: Boolean? = null
    @SerializedName("number")
    var number: Long? = null
    @SerializedName("numberOfElements")
    var numberOfElements: Long? = null
    @SerializedName("pageable")
    var pageable: Pageable? = null
    @SerializedName("size")
    var size: Long? = null
    @SerializedName("sort")
    var sort: Sort? = null
    @SerializedName("totalElements")
    var totalElements: Long? = null
    @SerializedName("totalPages")
    var totalPages: Long? = null

}
