package com.expert.operrwork.data.model.agency.settings.request

class SettingsRequest {

    var createdAt: Long = 0
    var updatedAt: Long = 0
    var createdByUsr: Any? = null
    var lastModifiedBy: String? = null
    var companyId: Any? = null
    var redirectPageId: Any? = null
    var id: Int? =null
    var username: String? = ""
    var gender: String? = ""
    var email: String? = ""
    var phone: String? = ""
    var ip: Any? = null
    var status: Long = 0
    var role: RoleBean? = null
    var firstName: String? = null
    var middleName: Any? = null
    var lastName: String? = null
    var secondaryPhone: Any? = null
    var profilePhoto: Any? = null
    var deviceAddress: Any? = null
    var rememberToken: String? = ""
    var postDepartmentLevel: Any? = null
    var postAgencyLevel: Any? = null
    var postCompanyLevel: Any? = null
    var fcmToken: String? = ""
    var agency: AgencyBean? = null
    var company: CompanyBeanX? = null
    var isIpRestricted: Boolean = false
    var isIpHourlyRestricted: Boolean = false
    var renewPasswordInterval: Int = 0
    var lastPasswordChangedAt: Long = 0
    var isOverrideOTLimit: Boolean = false
    var isChangeStatusNotification: Boolean = false
    var isEmailNotification: Boolean = false
    var isSMSNotification: Boolean = false
    var did: Any? = null
    var menuList: List<String>? = null
    var actionList: List<String>? = null
    var listIpRestricted: ArrayList<ListIpRestrictedBean>? = null
    var listIpHourlyRestricted: ArrayList<ListIpHourlyRestrictedBean>? = null
    var listGroupIds: List<*>? = null
    var listSelectedEmailPositionId: List<Int>? = null
    var listSelectedSMSPositionId: List<Int>? = null

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

    class AgencyBean {

        var createdAt: Long = 0
        var updatedAt: Long = 0
        var createdByUsr: Any? = null
        var lastModifiedBy: Any? = null
        var id: Int = 0
        var licenseNo: String? = null
        var name: String? = null
        var trackingMode: Any? = null
        var contactPerson: String? = null
        var email: String? = null
        var addressOne: String? = null
        var addressTwo: Any? = null
        var city: String? = null
        var state: String? = null
        var zipcode: String? = null
        var timezone: String? = null
        var phone: String? = null
        var holiday: String? = null
        var fax: String? = null
        var status: Int = 0
        var capacityNum: Any? = null
        var minimumWage: Any? = null
        var cctvUrl: Any? = null
        var agreementSignature: Any? = null
        var meals: Any? = null
        var mainWhiteLabel: Any? = null
        var upperLeftLabel: Any? = null
        var is2ndVerificationEnable: Boolean = false
        var offDaysMultiple: Any? = null
        var company: CompanyBean? = null
        var agencyType: Int = 0
        var createdBy: String? = null
    }

        class CompanyBean {


            var createdAt: Long = 0
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
            var fax: Int = 0
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


    class CompanyBeanX {


        var createdAt: Long = 0
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
        var fax: Int = 0
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

    class ListIpRestrictedBean {

        var id: Int = 0
        var position: Int = 0
        var ipAddress: String? = null
    }

    class ListIpHourlyRestrictedBean {

        var timeStart: String? = null
        var timeEnd: String? = null
        var ipAddress: String? = null
        var id: Int = 0
        var dayOfWeek: Int = 0
        var isChecked: Boolean = false
    }
}
