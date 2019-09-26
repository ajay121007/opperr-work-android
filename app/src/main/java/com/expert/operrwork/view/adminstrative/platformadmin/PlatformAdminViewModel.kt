package com.expert.operrwork.view.adminstrative.platformadmin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.expert.operrwork.base.BaseActivity
import com.expert.operrwork.base.BaseViewModel
import com.expert.operrwork.data.model.createPlatformAdmin.request.CreatePlatformAdminRequest
import com.expert.operrwork.data.model.createPlatformAdmin.response.CreatePlatformAdminResponse
import com.expert.operrwork.data.model.response.allplatformadmin.AllPlateFormAdmin
import com.expert.operrwork.data.model.response.getAgencyAdminById.GetAdminById
import com.expert.operrwork.data.model.role.GetRoleResponse
import com.expert.operrwork.data.model.updatePlatformAdmin.request.UpdatePlatformAdminRequest
import com.expert.operrwork.data.model.updatePlatformAdmin.response.UpdatePlatformAdminResponse
import com.expert.operrwork.remote.APIClient
import com.expert.operrwork.remote.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlatformAdminViewModel(var mBaseActivity: BaseActivity) : BaseViewModel() {

    private var getRoleResponse = MutableLiveData<GetRoleResponse>()
    private var allPlateFormAdmin = MutableLiveData<AllPlateFormAdmin>()
    private var platformAdminById = MutableLiveData<GetAdminById>()
    private var updatePlatformAdminResponse = MutableLiveData<UpdatePlatformAdminResponse>()
    private var createPlatformAdminResponse = MutableLiveData<CreatePlatformAdminResponse>()
    val apiServices = APIClient().getClient(mBaseActivity).create(RetrofitInterface::class.java)

    fun getAllPlateFormAdmin() {
        onProgressShow(mBaseActivity)
        val call = apiServices.getAllPlateFormAdmin()

        call.enqueue(object : Callback<AllPlateFormAdmin> {
            override fun onResponse(
                call: Call<AllPlateFormAdmin>?,
                response: Response<AllPlateFormAdmin>?
            ) {
                if (response!!.isSuccessful) {
                    allPlateFormAdmin.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<AllPlateFormAdmin>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun updatePlatformAdmin(updatePlatformAdminRequest:UpdatePlatformAdminRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.updatePlatformAdmin(updatePlatformAdminRequest)

        call.enqueue(object : Callback<UpdatePlatformAdminResponse> {
            override fun onResponse(
                call: Call<UpdatePlatformAdminResponse>?,
                response: Response<UpdatePlatformAdminResponse>?
            ) {
                if (response!!.isSuccessful) {
                    updatePlatformAdminResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<UpdatePlatformAdminResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }
    fun getPlatformAdminById(id:String) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getAdminById(id)

        call.enqueue(object : Callback<GetAdminById> {
            override fun onResponse(
                call: Call<GetAdminById>?,
                response: Response<GetAdminById>?
            ) {
                if (response!!.isSuccessful) {
                    platformAdminById.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetAdminById>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun createPlatformAdmin(createPlatformAdminRequest: CreatePlatformAdminRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.createPlatformAdmin(createPlatformAdminRequest)

        call.enqueue(object : Callback<CreatePlatformAdminResponse> {
            override fun onResponse(
                call: Call<CreatePlatformAdminResponse>?,
                response: Response<CreatePlatformAdminResponse>?
            ) {
                if (response!!.isSuccessful) {
                    createPlatformAdminResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<CreatePlatformAdminResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getPlatformAdminRole() {
        onProgressShow(mBaseActivity)
        val call = apiServices.getPlatformAdminRole()

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

    fun getRoleResponse(): LiveData<GetRoleResponse> {
        return getRoleResponse
    }

    fun getAllPlateFormAdminList(): LiveData<AllPlateFormAdmin> {
        return allPlateFormAdmin
    }
    fun getPlatformAdminDetails(): LiveData<GetAdminById> {
        return platformAdminById
    }

    fun updatePlatformAdminResponse(): LiveData<UpdatePlatformAdminResponse> {
        return updatePlatformAdminResponse
    }

    fun createPlatformAdminResponse(): LiveData<CreatePlatformAdminResponse> {
        return createPlatformAdminResponse
    }
}