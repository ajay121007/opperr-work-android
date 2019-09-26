package com.expert.operrwork.data.model.agency

class IPRestrictionTimeBean {


    var ip_list: ArrayList<IPListBean>? = ArrayList()

    class IPListBean {
        var ip: String? = ""
        var days: String? = ""
        var startTime: String? = ""
        var endTime: String? = ""
        var checked: Boolean = false

    }
}
