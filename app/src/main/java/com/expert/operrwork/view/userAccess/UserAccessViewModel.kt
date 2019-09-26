package com.expert.operrwork.view.userAccess

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.expert.operrwork.remote.RetrofitInterface
import com.expert.operrwork.base.BaseActivity
import com.expert.operrwork.base.BaseViewModel
import com.expert.operrwork.data.model.forgotPassword.ForgotPasswordRequest
import com.expert.operrwork.data.model.login.LoginRequest
import com.expert.operrwork.data.model.login.loginResponse.LoginResponse
import com.expert.operrwork.data.model.forgotPassword.ForgotResponse
import com.expert.operrwork.remote.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAccessViewModel(var mBaseActivity: BaseActivity) : BaseViewModel() {

    private var loginResponse = MutableLiveData<LoginResponse>()
    private var forgotResponse = MutableLiveData<ForgotResponse>()
    val apiServices = APIClient().getClient(mBaseActivity).create(RetrofitInterface::class.java)

    fun getLogin(loginRequest: LoginRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.getLogin(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                if (response!!.isSuccessful) {
                    loginResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                   //mBaseActivity.showError(mBaseActivity.resources.getString(R.string.msg_valid_login))
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun forgotPassword(request: ForgotPasswordRequest) {
        onProgressShow(mBaseActivity)
        val call = apiServices.forgot(request)

        call.enqueue(object : Callback<ForgotResponse> {
            override fun onResponse(call: Call<ForgotResponse>?, response: Response<ForgotResponse>?) {
                if (response!!.isSuccessful) {
                    forgotResponse.value = response.body()
                } else {
                    mBaseActivity.showApiError(response.code().toString())
                   //mBaseActivity.showError(mBaseActivity.resources.getString(R.string.msg_valid_login))
                }
                onProgressHide()
            }

            override fun onFailure(call: Call<ForgotResponse>, t: Throwable?) {
                mBaseActivity.showApiError(t!!.message.toString())
                onProgressHide()
            }
        })
    }

    fun getLoginResponse(): LiveData<LoginResponse> {
        return loginResponse
    }
    fun getForgotResponse(): LiveData<ForgotResponse> {
        return forgotResponse
    }
}