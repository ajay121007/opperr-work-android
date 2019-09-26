package com.expert.operrwork.data.model.agency

class DaysBean {


    var days_list: ArrayList<DaysListBean>? = ArrayList()

    class DaysListBean {
        var id: String? = ""
        var days: String? = ""
        var checked: Boolean = false
        
    }
}
