package com.expert.operrwork.view.adminstrative.processMonitor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.expert.operrwork.base.BaseActivity
import com.expert.operrwork.base.BaseViewModel
import com.expert.operrwork.data.model.response.getUserProcess.GetUserProcessResponse
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.model.response.processMonitor.ProcessMonitorResponse
import com.expert.operrwork.remote.APIClient
import com.expert.operrwork.remote.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProcessMonitorViewModel(var mBaseActivity: BaseActivity) : BaseViewModel() {

    private var processMonitorResponse = MutableLiveData<ProcessMonitorResponse>()
    private var allCompanyResponse = MutableLiveData<GetAllCompanyDropDownResponse>()
    private var getAgencyById = MutableLiveData<GetAgencyByCompId>()
    private var getUserProcessResponse = MutableLiveData<GetUserProcessResponse>()
    val apiServices = APIClient().getClient(mBaseActivity).create(RetrofitInterface::class.java)

    fun allUserProcessWithFilter(agencyId:String,status:String,duration:String,startedBy:String,fromDate:String,toDate:String,page:Int,pageSize:Int) {
        onProgressShow(mBaseActivity)
        val call = apiServices.allUserProcessWithFilter(agencyId,status,duration,startedBy,fromDate,toDate,page,pageSize)

        call.enqueue(object : Callback<ProcessMonitorResponse> {
            override fun onResponse(
                call: Call<ProcessMonitorResponse>?,
                response: Response<ProcessMonitorResponse>?
            ) {
                if (response!!.isSuccessful) {
                    processMonitorResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<ProcessMonitorResponse>, t: Throwable?) {
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

    fun getProcessMonitorById(id:String) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getProcessMonitorById(id)

        call.enqueue(object : Callback<GetUserProcessResponse> {
            override fun onResponse(
                call: Call<GetUserProcessResponse>?,
                response: Response<GetUserProcessResponse>?
            ) {
                if (response!!.isSuccessful) {
                    getUserProcessResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<GetUserProcessResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getAllUserProcessWithFilter(): LiveData<ProcessMonitorResponse> {
        return processMonitorResponse
    }
    fun getCompanyDropDown(): LiveData<GetAllCompanyDropDownResponse> {
        return allCompanyResponse
    }

    fun getAgencyDropDown(): LiveData<GetAgencyByCompId> {
        return getAgencyById
    }
    fun getUserProcessMonitor(): LiveData<GetUserProcessResponse> {
        return getUserProcessResponse
    }

}