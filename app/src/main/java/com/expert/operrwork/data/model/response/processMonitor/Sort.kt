package com.expert.operrwork.data.model.response.processMonitor

import com.google.gson.annotations.SerializedName

class Sort {

    @SerializedName("sorted")
    var sorted: Boolean? = null
    @SerializedName("unsorted")
    var unsorted: Boolean? = null

}
