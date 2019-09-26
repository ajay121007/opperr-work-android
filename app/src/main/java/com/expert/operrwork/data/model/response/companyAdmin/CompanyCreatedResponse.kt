package com.expert.operrwork.data.model.response.companyAdmin

class CompanyCreatedResponse {

    /**
     * status : SUCCESS
     * data : {"companies":[{"id":1,"name":"Clean Air Car Service","status":0},{"id":2,"name":"Qian Qian","status":0},{"id":3,"name":"TatvaOne","status":0},{"id":4,"name":"Company 1","status":0},{"id":5,"name":"New Test","status":0},{"id":6,"name":"Test la","status":0},{"id":7,"name":"Tatva","status":0},{"id":8,"name":"Yuri","status":0}],"statuses":[{"id":null,"name":null,"status":1}],"createdByUsers":[{"id":1,"name":"defaultadmin","status":0},{"id":3,"name":"fiona102","status":0},{"id":4,"name":"Test4321","status":0},{"id":5,"name":"james","status":0},{"id":6,"name":"Test1400","status":0},{"id":7,"name":"Jeanje12","status":0},{"id":9,"name":"pfjjfjdljfdljfdljjdflkjljf","status":0},{"id":10,"name":"testtest","status":0},{"id":11,"name":"abcdabc23","status":0},{"id":12,"name":"vikas123","status":0},{"id":13,"name":"vikas1234","status":0},{"id":14,"name":"Garcon12","status":0},{"id":15,"name":"Garcon13","status":0},{"id":16,"name":"sanchez00","status":0},{"id":17,"name":"defaultadmin2","status":0},{"id":18,"name":"defaultadmin3","status":0},{"id":19,"name":"defaultadmin12","status":0},{"id":20,"name":"defaultadmin122","status":0},{"id":21,"name":"Jacques1","status":0},{"id":22,"name":"testtesttest","status":0},{"id":23,"name":"PlatAdmin","status":0},{"id":25,"name":"bhaveshkk","status":0},{"id":26,"name":"usernametets","status":0},{"id":27,"name":"abcdxzta","status":0},{"id":28,"name":"123456789","status":0},{"id":29,"name":"amitcompnayadmin","status":0},{"id":30,"name":"namoagencyadmin","status":0},{"id":31,"name":"198admin","status":0},{"id":32,"name":"today3213","status":0},{"id":33,"name":"adnanagency","status":0},{"id":34,"name":"qianqian","status":0},{"id":35,"name":"companyadmin1","status":0},{"id":37,"name":"defaultagency","status":0},{"id":38,"name":"Gold1234","status":0},{"id":39,"name":"punchinout","status":0},{"id":43,"name":"ygoncharov","status":0},{"id":44,"name":"PlatformAd","status":0},{"id":45,"name":"CompanyAd","status":0},{"id":46,"name":"YuriAgencyAd","status":0},{"id":47,"name":"test1234","status":0},{"id":48,"name":"yuriCompanyAd","status":0},{"id":49,"name":"companyAdmin","status":0},{"id":50,"name":"MobileApp","status":0},{"id":51,"name":"agencyad","status":0},{"id":52,"name":"testcompany","status":0},{"id":53,"name":"testagency","status":0},{"id":54,"name":"username1991","status":0},{"id":55,"name":"mobile12","status":0},{"id":56,"name":"Jason123","status":0},{"id":57,"name":"testingadmins","status":0},{"id":58,"name":"amitcompnayadmin","status":0},{"id":59,"name":"namoagencyadmin","status":0},{"id":60,"name":"kolo1234","status":0},{"id":61,"name":"jackclean","status":0},{"id":62,"name":"dkv12345","status":0},{"id":63,"name":"prachi123","status":0},{"id":64,"name":"prachijaiswal","status":0},{"id":65,"name":"vikaspatel4","status":0},{"id":66,"name":"vikaspatel5","status":0},{"id":67,"name":"vikaspatel6","status":0},{"id":68,"name":"carlos12","status":0},{"id":69,"name":"vikaspatel7","status":0},{"id":70,"name":"vikaspatel8","status":0}]}
     * message : successfully done
     */

    var status: String? = null
    var data: DataBean? = null
    var message: String? = null


    class DataBean {
        var companies: List<CompaniesBean>? = null
        var statuses: List<StatusesBean>? = null
        var createdByUsers: List<CreatedByUsersBean>? = null

    }

        class CompaniesBean {
            /**
             * id : 1
             * name : Clean Air Car Service
             * status : 0
             */

            var id: Int = 0
            var name: String? = null
            var status: Int = 0
        }

        class StatusesBean {
            /**
             * id : null
             * name : null
             * status : 1
             */

            var id: Any? = null
            var name: Any? = null
            var status: Int = 0
        }

        class CreatedByUsersBean {
            /**
             * id : 1
             * name : defaultadmin
             * status : 0
             */

            var id: Int = 0
            var name: String? = null
            var status: Int = 0
        }
    }
