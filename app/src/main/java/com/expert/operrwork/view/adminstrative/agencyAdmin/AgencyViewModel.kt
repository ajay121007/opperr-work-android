package com.expert.operrwork.view.adminstrative.agencyAdmin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.expert.operrwork.base.BaseActivity
import com.expert.operrwork.base.BaseViewModel
import com.expert.operrwork.data.model.admin.updateAdminRequest.UpdateAdminRequest
import com.expert.operrwork.data.model.admin.updateAdminResponse.UpdateAdminResponse
import com.expert.operrwork.data.model.adminDropDown.GetAdminDropDown
import com.expert.operrwork.data.model.agency.settings.request.SettingsRequest
import com.expert.operrwork.data.model.agency.settings.response.SettingsResponse
import com.expert.operrwork.data.model.agencyAdminById.AgencyAdminDetailById
import com.expert.operrwork.data.model.companyAdminById.GetCompanyAdminById
import com.expert.operrwork.data.model.group.getAllGroup.GetAllGroupResponse
import com.expert.operrwork.data.model.loginNameById.LoginName
import com.expert.operrwork.data.model.createAdmin.CreateAdminRequest
import com.expert.operrwork.data.model.response.adminCreatedDropDown.AdminCreatedDropDown
import com.expert.operrwork.data.model.response.bringEmployee.EmployeeDropDownResponse
import com.expert.operrwork.data.model.response.getAllAgencyAdmin.GetAllAgencyAdminResponse
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.model.role.GetRoleResponse
import com.expert.operrwork.remote.APIClient
import com.expert.operrwork.remote.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgencyViewModel(var mBaseActivity: BaseActivity) : BaseViewModel() {

    private var allCompanyResponse = MutableLiveData<GetAllCompanyDropDownResponse>()
    private var getRoleResponse = MutableLiveData<GetRoleResponse>()
    private var getAgencyById = MutableLiveData<GetAgencyByCompId>()
    private var GetAgencyDropDown = MutableLiveData<GetAdminDropDown>()
    private var loginNameDropDown = MutableLiveData<LoginName>()
    private var adminDropDown = MutableLiveData<AdminCreatedDropDown>()
    private var addAgencyAdminResponse = MutableLiveData<UpdateAdminResponse>()
    private var getAllAgencyAdminResponse = MutableLiveData<GetAllAgencyAdminResponse>()
    private var getAgencyAdminById = MutableLiveData<AgencyAdminDetailById>()
    private var updateAgencyAdminResponse = MutableLiveData<UpdateAdminResponse>()
    private var getAdminByIdModel = MutableLiveData<GetCompanyAdminById>()
    private var employeeDropDownResponse = MutableLiveData<EmployeeDropDownResponse>()
    private var groupsModel = MutableLiveData<GetAllGroupResponse>()
    private var updateSettings = MutableLiveData<SettingsResponse>()

    val apiServices = APIClient().getClient(mBaseActivity).create(RetrofitInterface::class.java)


    fun getAdminById(id:String) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getCompanyById(id)

        call.enqueue(object : Callback<GetCompanyAdminById> {
            override fun onResponse(call: Call<GetCompanyAdminById>?, response: Response<GetCompanyAdminById>?) {
                if (response!!.isSuccessful) {
                    getAdminByIdModel.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetCompanyAdminById>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getAgencyAdminRole() {
        onProgressShow(mBaseActivity)
        val call = apiServices.getAgencyAdminRole()

        call.enqueue(object : Callback<GetRoleResponse> {
            override fun onResponse(
                call: Call<GetRoleResponse>?,
                response: Response<GetRoleResponse>?
            ) {
                if (response!!.isSuccessful) {
                    getRoleResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetRoleResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getComapnyListDropDown() {
        onProgressShow(mBaseActivity)
        val call = apiServices.getAllCompany()

        call.enqueue(object : Callback<GetAllCompanyDropDownResponse> {
            override fun onResponse(
                call: Call<GetAllCompanyDropDownResponse>?,
                response: Response<GetAllCompanyDropDownResponse>?
            ) {
                if (response!!.isSuccessful) {
                    allCompanyResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetAllCompanyDropDownResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getAgencyByCompanyIdDropDown(companyId: String) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getAgencyByCompId(companyId)

        call.enqueue(object : Callback<GetAgencyByCompId> {
            override fun onResponse(
                call: Call<GetAgencyByCompId>?,
                response: Response<GetAgencyByCompId>?
            ) {
                if (response!!.isSuccessful) {
                    getAgencyById.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetAgencyByCompId>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }
    fun loginNameDropDown(companyId:String,type:String) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getLoginNameDropDown(
            companyId,type
        )

        call.enqueue(object : Callback<LoginName> {
            override fun onResponse(
                call: Call<LoginName>?,
                response: Response<LoginName>?
            ) {
                if (response!!.isSuccessful) {
                    loginNameDropDown.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<LoginName>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun adminCreatedDropDown() {
        onProgressShow(mBaseActivity)
        val call = apiServices.adminCreatedDropDown()
        call.enqueue(object : Callback<AdminCreatedDropDown> {
            override fun onResponse(
                call: Call<AdminCreatedDropDown>?,
                response: Response<AdminCreatedDropDown>?
            ) {
                if (response!!.isSuccessful) {
                    adminDropDown.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<AdminCreatedDropDown>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun updateSettings(settingsRequest: SettingsRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.updateSettings(settingsRequest)

        call.enqueue(object : Callback<SettingsResponse> {
            override fun onResponse(
                call: Call<SettingsResponse>?,
                response: Response<SettingsResponse>?
            ) {
                if (response!!.isSuccessful) {
                    updateSettings.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<SettingsResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getAllAgencyAdmin(
        companyId:String,
        agencyId:String,
        createdBy:String,
        status:String,
        page: Int,
        pageSize:String) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getAllAgencyAdmin(companyId,agencyId,status,createdBy,page,pageSize)

        call.enqueue(object : Callback<GetAllAgencyAdminResponse> {
            override fun onResponse(call: Call<GetAllAgencyAdminResponse>?, response: Response<GetAllAgencyAdminResponse>?) {
                if (response!!.isSuccessful) {
                    getAllAgencyAdminResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetAllAgencyAdminResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getAgencyAdminById(agencyId:String) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getAgencyAdminById(agencyId)

        call.enqueue(object : Callback<AgencyAdminDetailById> {
            override fun onResponse(call: Call<AgencyAdminDetailById>?, response: Response<AgencyAdminDetailById>?) {
                if (response!!.isSuccessful) {
                    getAgencyAdminById.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<AgencyAdminDetailById>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }
    fun editAgency(updateAgencyAdminRequest: UpdateAdminRequest) {
    onProgressShow(mBaseActivity)
    val call =
        apiServices.updateAgencyAdmin(updateAgencyAdminRequest)

    call.enqueue(object : Callback<UpdateAdminResponse> {

        override fun onResponse(
            call: Call<UpdateAdminResponse>?,
            response: Response<UpdateAdminResponse>?
        ) {
            if (response!!.isSuccessful) {
                updateAgencyAdminResponse.value = response.body()
            } else {
                mBaseActivity.showApiError(response.code().toString())
            }
            onProgressHide()
        }

        override fun onFailure(call: Call<UpdateAdminResponse>, t: Throwable?) {
            mBaseActivity.showApiError(t!!.message.toString())
            onProgressHide()
        }
    })
}

    fun getEmployeeDropDown(companyId:String) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getEmployeeDropDown(companyId)

        call.enqueue(object : Callback<EmployeeDropDownResponse> {
            override fun onResponse(
                call: Call<EmployeeDropDownResponse>?,
                response: Response<EmployeeDropDownResponse>?
            ) {
                if (response!!.isSuccessful) {
                    employeeDropDownResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<EmployeeDropDownResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getAllGroupsRequest(
        selectedCompanyId: String,
        selectedAgencyId: String
    ) {
        onProgressShow(mBaseActivity)
        val call = apiServices.
            getGroups( selectedCompanyId,selectedAgencyId)

        call.enqueue(object : Callback<GetAllGroupResponse> {
            override fun onResponse(call: Call<GetAllGroupResponse>?,
                                    response: Response<GetAllGroupResponse>?) {
                if (response!!.isSuccessful) {
                    groupsModel.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetAllGroupResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }


    fun addAgencyAdmin(createAdminRequest: CreateAdminRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.createAdmin(createAdminRequest)

        call.enqueue(object : Callback<UpdateAdminResponse> {
            override fun onResponse(
                call: Call<UpdateAdminResponse>?,
                response: Response<UpdateAdminResponse>?
            ) {
                if (response!!.isSuccessful) {
                    addAgencyAdminResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<UpdateAdminResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }


    fun getUpdateSettings(): LiveData<SettingsResponse> {
        return updateSettings
    }

    fun getCompanyDropDown(): LiveData<GetAllCompanyDropDownResponse> {
        return allCompanyResponse
    }

    fun GetAgencyDropDown(): LiveData<GetAdminDropDown> {
        return GetAgencyDropDown
    }
    fun getCreatedByDropDown(): LiveData<AdminCreatedDropDown> {
        return adminDropDown
    }
    fun getAllAgencyAdminResponse(): LiveData<GetAllAgencyAdminResponse> {
        return getAllAgencyAdminResponse
    }
    fun getAgencyAdminById(): LiveData<AgencyAdminDetailById> {
        return getAgencyAdminById
    }
    fun getAgencyDropDownByCompanyId(): LiveData<GetAdminDropDown> {
        return GetAgencyDropDown
    }
    fun getUpdateAgencyAdmin(): LiveData<UpdateAdminResponse> {
        return updateAgencyAdminResponse
    }

    fun getAddAgencyAdmin(): LiveData<UpdateAdminResponse> {
        return addAgencyAdminResponse
    }
    fun getAgencyDropDownById(): LiveData<GetAgencyByCompId> {
        return getAgencyById
    }

    fun getLoginNameDropDown(): LiveData<LoginName> {
        return loginNameDropDown
    }
    fun getAdminDetails(): LiveData<GetCompanyAdminById> {
        return getAdminByIdModel
    }

    fun getEmployeeDropDownResponse(): LiveData<EmployeeDropDownResponse> {
        return employeeDropDownResponse
    }
    fun getAllGroups(): LiveData<GetAllGroupResponse> {
        return groupsModel
    }
    fun getRoleResponse(): LiveData<GetRoleResponse> {
        return getRoleResponse
    }



}