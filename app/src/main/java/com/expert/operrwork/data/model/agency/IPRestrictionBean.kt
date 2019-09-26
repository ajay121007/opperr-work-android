package com.expert.operrwork.data.model.agency

class IPRestrictionBean {


    var ip_list: ArrayList<IPListBean>? =  ArrayList()

    class IPListBean {
        var ip: String? = ""
        var checked: Boolean = false



    }
}
