package com.expert.operrwork.data.model.loginNameById

class LoginName {

    /**
     * status : SUCCESS
     * data : [{"id":3,"name":"fiona102"},{"id":4,"name":"Test4321"},{"id":6,"name":"Test1400"},{"id":10,"name":"testtest"},{"id":11,"name":"abcdabc23"},{"id":17,"name":"defaultadmin2"},{"id":18,"name":"defaultadmin3"},{"id":19,"name":"defaultadmin12"},{"id":20,"name":"defaultadmin122"},{"id":21,"name":"Jacques1"},{"id":22,"name":"testtesttest"},{"id":25,"name":"bhaveshkk"},{"id":26,"name":"usernametets"},{"id":27,"name":"abcdxzta"},{"id":28,"name":"123456789"},{"id":30,"name":"namoagencyadmin"},{"id":31,"name":"198admin"},{"id":32,"name":"today3213"},{"id":33,"name":"adnanagency"},{"id":34,"name":"qianqian"},{"id":37,"name":"defaultagency"},{"id":38,"name":"Gold1234"},{"id":47,"name":"test1234"},{"id":51,"name":"agencyad"},{"id":53,"name":"testagency"},{"id":56,"name":"Jason123"},{"id":59,"name":"namoagencyadmin"},{"id":69,"name":"vikaspatel7"}]
     * message : successfully done
     */

    var status: String? = null
    var message: String? = null
    var data: List<DataBean>? = null


    class DataBean {
        /**
         * id : 3
         * name : fiona102
         */

        var id: Int = 0
        var name: String? = null
    }
}
