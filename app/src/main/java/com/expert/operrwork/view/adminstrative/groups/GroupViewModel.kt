package com.expert.operrwork.view.adminstrative.groups

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.expert.operrwork.base.BaseActivity
import com.expert.operrwork.base.BaseViewModel
import com.expert.operrwork.data.model.adminDropDown.GetAdminDropDown
import com.expert.operrwork.data.model.group.addUpdateGroup.request.AddUpdateGroupRequest
import com.expert.operrwork.data.model.group.addUpdateGroup.response.AddUpdateGroupResponse
import com.expert.operrwork.data.model.group.getAllGroup.GetAllGroupResponse
import com.expert.operrwork.data.model.groupDetailById.GetGroupDetailById
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.remote.APIClient
import com.expert.operrwork.remote.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GroupViewModel(var mBaseActivity: BaseActivity) : BaseViewModel() {

    private var getAgencyById = MutableLiveData<GetAgencyByCompId>()
    private var allCompanyResponse = MutableLiveData<GetAllCompanyDropDownResponse>()
    private var getGroupByIdModel = MutableLiveData<GetGroupDetailById>()
    private var agencyDropDown = MutableLiveData<GetAdminDropDown>()
    private var getAdminDropDown = MutableLiveData<GetAdminDropDown>()
    private var addGroupResponse = MutableLiveData<AddUpdateGroupResponse>()
    private var groupsModel = MutableLiveData<GetAllGroupResponse>()
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
    fun getAdminDropDown() {
        onProgressShow(mBaseActivity)
        val call = apiServices.getAdminDropDown()

        call.enqueue(object : Callback<GetAdminDropDown> {
            override fun onResponse(
                call: Call<GetAdminDropDown>?,
                response: Response<GetAdminDropDown>?
            ) {
                if (response!!.isSuccessful) {
                    getAdminDropDown.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetAdminDropDown>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun addGroup(addGroupRequest: AddUpdateGroupRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.addGroup(addGroupRequest)

        call.enqueue(object : Callback<AddUpdateGroupResponse> {
            override fun onResponse(
                call: Call<AddUpdateGroupResponse>?,
                response: Response<AddUpdateGroupResponse>?
            ) {
                if (response!!.isSuccessful) {
                    addGroupResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<AddUpdateGroupResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun updateGroup(addGroupRequest: AddUpdateGroupRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.updateGroup(addGroupRequest)

        call.enqueue(object : Callback<AddUpdateGroupResponse> {
            override fun onResponse(
                call: Call<AddUpdateGroupResponse>?,
                response: Response<AddUpdateGroupResponse>?
            ) {
                if (response!!.isSuccessful) {
                    addGroupResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<AddUpdateGroupResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getGroupById(id:String) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getGroupById(id)

        call.enqueue(object : Callback<GetGroupDetailById> {
            override fun onResponse(call: Call<GetGroupDetailById>?, response: Response<GetGroupDetailById>?) {
                if (response!!.isSuccessful) {
                    getGroupByIdModel.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetGroupDetailById>, t: Throwable?) {
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

    fun getCompanyDropDown(): LiveData<GetAllCompanyDropDownResponse> {
        return allCompanyResponse
    }

    fun getAllGroups(): LiveData<GetAllGroupResponse> {
        return groupsModel
    }
    fun getAgencies(): LiveData<GetAdminDropDown> {
        return agencyDropDown
    }
    fun geGroup(): LiveData<GetGroupDetailById> {
        return getGroupByIdModel
    }
    fun getAdminDropDownResponse(): LiveData<GetAdminDropDown> {
        return getAdminDropDown
    }

    fun getAgencyAdminResponse(): LiveData<GetAgencyByCompId> {
        return getAgencyById
    }
    fun addUpdateGroupResponse(): LiveData<AddUpdateGroupResponse> {
        return addGroupResponse
    }

}