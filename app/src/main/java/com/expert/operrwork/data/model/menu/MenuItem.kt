package com.expert.operrwork.data.model.menu

class MenuItem {


    var menu_list: List<MenuListBean>? = null

    class MenuListBean {
        var id: String? = null
        var name: String? = null
        var label: String? = null
        var routerlink: String? = null
        var order: String? = null
        var checked: Boolean = false



    }
}
