package com.expert.operrwork.data.model.response.companyAdmin

class CompanyAdminResponse {

    var status: String? = null
    var data: DataBean? = null
    var message: String? = null

    class DataBean {
        var isLast: Boolean = false
        var totalElements: Int = 0
        var totalPages: Int = 0
        var sort: SortBeanX? = null
        var numberOfElements: Int = 0
        var isFirst: Boolean = false
        var size: Int = 0
        var number: Int = 0
        var content: List<ContentBean>? = null
    }

    class PageableBean {
        var sort: SortBean? = null
        var pageSize: Int = 0
        var pageNumber: Int = 0
        var offset: Int = 0
        var isPaged: Boolean = false
        var isUnpaged: Boolean = false
    }

    class SortBean {

        var isUnsorted: Boolean = false
        var isSorted: Boolean = false
    }

    class SortBeanX {

        var isUnsorted: Boolean = false
        var isSorted: Boolean = false
    }

    class ContentBean {
        var updatedAt: Long = 0
        var createdByUsr: Any? = null
        var lastModifiedBy: String? = null
        var companyId: Any? = null
        var redirectPageId: Any? = null
        var id: Int = 0
        var username: String? = null
        var gender: String? = null
        var email: String? = null
        var phone: String? = null
        var ip: Any? = null
        var status: Int = 0
        var role: RoleBean? = null
        var firstName: String? = null
        var middleName: Any? = null
        var lastName: String? = null
        var secondaryPhone: String? = null
        var profilePhoto: Any? = null
        var deviceAddress: Any? = null
        var rememberToken: String? = null
        var postDepartmentLevel: Any? = null
        var postAgencyLevel: Any? = null
        var postCompanyLevel: Any? = null
        var fcmToken: Any? = null
        var agency: Any? = null
        var company: CompanyBean? = null
        var isIsIpRestricted: Boolean = false
        var isIsIpHourlyRestricted: Boolean = false
        var renewPasswordInterval: Any? = null
        var lastPasswordChangedAt: Long = 0
        var listGroupIds: Any? = null
        var isIsOverrideOTLimit: Boolean = false
        var isIsChangeStatusNotification: Boolean = false
        var isIsEmailNotification: Boolean = false
        var isIsSMSNotification: Boolean = false
        var did: Any? = null
        var listSelectedEmailPositionId: Any? = null
        var listSelectedSMSPositionId: Any? = null
        var menuList: List<String>? = null
        var actionList: List<String>? = null
        var listIpRestricted: List<*>? = null
        var listIpHourlyRestricted: List<*>? = null
    }

    class RoleBean {

        var createdAt: Long = 0
        var updatedAt: Long = 0
        var createdByUsr: Any? = null
        var lastModifiedBy: Any? = null
        var id: Int = 0
        var level: Int = 0
        var levelName: String? = null
        var status: Int = 0
        var defaultVisibleList: Any? = null
        var defaultActionList: Any? = null
    }

    class CompanyBean {
        var updatedAt: Long = 0
        var createdByUsr: Any? = null
        var lastModifiedBy: Any? = null
        var id: Int = 0
        var licenseNumber: String? = null
        var federalTax: Any? = null
        var federalTaxStart: Any? = null
        var federalTaxExpire: Any? = null
        var federalTaxStatus: Any? = null
        var stateTax: Any? = null
        var stateTaxStart: Any? = null
        var stateTaxExpire: Any? = null
        var stateTaxStatus: Any? = null
        var name: String? = null
        var phone: String? = null
        var email: String? = null
        var fax: Any? = null
        var addressOne: String? = null
        var addressTwo: Any? = null
        var worktimeStart: Int = 0
        var worktimeEnd: Int = 0
        var city: String? = null
        var state: String? = null
        var zipcode: String? = null
        var status: Int = 0
        var type: Any? = null
        var daysWork: String? = null
        var timezone: Any? = null
    }
}
