package com.expert.operrwork.view.adminstrative.companyAdmin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.expert.operrwork.base.BaseActivity
import com.expert.operrwork.base.BaseViewModel
import com.expert.operrwork.data.model.admin.updateAdminRequest.UpdateAdminRequest
import com.expert.operrwork.data.model.admin.updateAdminResponse.UpdateAdminResponse
import com.expert.operrwork.data.model.companyAdminById.GetCompanyAdminById
import com.expert.operrwork.data.model.createAdmin.CreateAdminRequest
import com.expert.operrwork.data.model.response.bringEmployee.EmployeeDropDownResponse
import com.expert.operrwork.data.model.response.companyAdmin.CompanyAdminResponse
import com.expert.operrwork.data.model.response.companyAdmin.CompanyCreatedResponse
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.model.role.GetRoleResponse
import com.expert.operrwork.remote.APIClient
import com.expert.operrwork.remote.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class   CompanyViewModel(var mBaseActivity: BaseActivity) : BaseViewModel() {

    private var addCompanyAdminResponse = MutableLiveData<UpdateAdminResponse>()
    private var getAdminByIdModel = MutableLiveData<GetCompanyAdminById>()
    private var getRoleResponse = MutableLiveData<GetRoleResponse>()
    private var allCompanyResponse = MutableLiveData<GetAllCompanyDropDownResponse>()
    private var companyDetailResponse = MutableLiveData<GetCompanyAdminById>()
    private var createdDropDown = MutableLiveData<CompanyCreatedResponse>()
    private var employeeDropDownResponse = MutableLiveData<EmployeeDropDownResponse>()
    private var getAdminCompanies = MutableLiveData<CompanyAdminResponse>()
    val apiServices = APIClient().getClient(mBaseActivity).create(RetrofitInterface::class.java)

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

    fun getCompanyAdminRole() {
        onProgressShow(mBaseActivity)
        val call = apiServices.getCompanyAdminRole()

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
    fun addCompanyAdmin(createAdminRequest: CreateAdminRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.createAdmin(createAdminRequest)

        call.enqueue(object : Callback<UpdateAdminResponse> {
            override fun onResponse(
                call: Call<UpdateAdminResponse>?,
                response: Response<UpdateAdminResponse>?
            ) {
                if (response!!.isSuccessful) {
                    addCompanyAdminResponse.value = response.body()
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
    fun editCompanyAdmin(updateAdminRequest: UpdateAdminRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.updateAgencyAdmin(updateAdminRequest)

        call.enqueue(object : Callback<UpdateAdminResponse> {
            override fun onResponse(
                call: Call<UpdateAdminResponse>?,
                response: Response<UpdateAdminResponse>?
            ) {
                if (response!!.isSuccessful) {
                    addCompanyAdminResponse.value = response.body()
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

    fun getCreatedDropDownList() {
        onProgressShow(mBaseActivity)
        val call = apiServices.getCreatedCompanyForAdmin()
        call.enqueue(object : Callback<CompanyCreatedResponse> {
            override fun onResponse(
                call: Call<CompanyCreatedResponse>?,
                response: Response<CompanyCreatedResponse>?
            ) {
                if (response!!.isSuccessful) {
                    createdDropDown.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<CompanyCreatedResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getAllCompaniesRequest(
        selectedCompanyId: String,
        selectedCreatedBy: String,
        searchKey: String,
        selectedStaus: String
    ) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getAllCompanyForAdmin( selectedCompanyId,selectedCreatedBy,searchKey,selectedStaus)

        call.enqueue(object : Callback<CompanyAdminResponse> {
            override fun onResponse(call: Call<CompanyAdminResponse>?, response: Response<CompanyAdminResponse>?) {
                if (response!!.isSuccessful) {
                    getAdminCompanies.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<CompanyAdminResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getCompanyDropDown(): LiveData<GetAllCompanyDropDownResponse> {
        return allCompanyResponse
    }

    fun getAllCompanies(): LiveData<CompanyAdminResponse> {
        return getAdminCompanies
    }
    fun getCreatedDropDown(): LiveData<CompanyCreatedResponse> {
        return createdDropDown
    }
    fun getCompanyDetails(): LiveData<GetCompanyAdminById> {
        return getAdminByIdModel
    }
    fun getEmployeeDropDownResponse(): LiveData<EmployeeDropDownResponse> {
        return employeeDropDownResponse
    }
    fun getRoleResponse(): LiveData<GetRoleResponse> {
        return getRoleResponse
    }

    fun addUpdateCompanyResponse(): LiveData<UpdateAdminResponse> {
        return addCompanyAdminResponse
    }


}