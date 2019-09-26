package com.expert.operrwork.data.model.response.getAllAgencyAdmin

import com.google.gson.annotations.SerializedName
class Pageable {

    @SerializedName("offset")
    var offset: Long? = null
    @SerializedName("pageNumber")
    var pageNumber: Long? = null
    @SerializedName("pageSize")
    var pageSize: Long? = null
    @SerializedName("paged")
    var paged: Boolean? = null
    @SerializedName("sort")
    var sort: Sort? = null
    @SerializedName("unpaged")
    var unpaged: Boolean? = null

}
