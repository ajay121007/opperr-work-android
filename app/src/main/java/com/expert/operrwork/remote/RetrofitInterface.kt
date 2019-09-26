package com.expert.operrwork.remote

import com.expert.operrwork.data.model.admin.updateAdminRequest.UpdateAdminRequest
import com.expert.operrwork.data.model.admin.updateAdminResponse.UpdateAdminResponse
import com.expert.operrwork.data.model.adminDropDown.GetAdminDropDown
import com.expert.operrwork.data.model.agency.settings.request.SettingsRequest
import com.expert.operrwork.data.model.agency.settings.response.SettingsResponse
import com.expert.operrwork.data.model.agencyAdminById.AgencyAdminDetailById
import com.expert.operrwork.data.model.companyAdminById.GetCompanyAdminById
import com.expert.operrwork.data.model.createPlatformAdmin.request.CreatePlatformAdminRequest
import com.expert.operrwork.data.model.createPlatformAdmin.response.CreatePlatformAdminResponse
import com.expert.operrwork.data.model.group.addUpdateGroup.request.AddUpdateGroupRequest
import com.expert.operrwork.data.model.group.addUpdateGroup.response.AddUpdateGroupResponse
import com.expert.operrwork.data.model.group.getAllGroup.GetAllGroupResponse
import com.expert.operrwork.data.model.groupDetailById.GetGroupDetailById
import com.expert.operrwork.data.model.loginNameById.LoginName
import com.expert.operrwork.data.model.forgotPassword.ForgotPasswordRequest
import com.expert.operrwork.data.model.login.LoginRequest
import com.expert.operrwork.data.model.createAdmin.CreateAdminRequest
import com.expert.operrwork.data.model.response.GetAllAdminTypeResponse.GetAllAdminResponse
import com.expert.operrwork.data.model.login.loginResponse.LoginResponse
import com.expert.operrwork.data.model.response.adminCreatedDropDown.AdminCreatedDropDown
import com.expert.operrwork.data.model.response.allplatformadmin.AllPlateFormAdmin
import com.expert.operrwork.data.model.response.bringEmployee.EmployeeDropDownResponse
import com.expert.operrwork.data.model.response.companyAdmin.CompanyAdminResponse
import com.expert.operrwork.data.model.response.companyAdmin.CompanyCreatedResponse
import com.expert.operrwork.data.model.forgotPassword.ForgotResponse
import com.expert.operrwork.data.model.response.getAgencyAdminById.GetAdminById
import com.expert.operrwork.data.model.response.getAllAgencyAdmin.GetAllAgencyAdminResponse
import com.expert.operrwork.data.model.response.getUserProcess.GetUserProcessResponse
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.model.response.processMonitor.ProcessMonitorResponse
import com.expert.operrwork.data.model.role.GetRoleResponse
import com.expert.operrwork.data.model.updatePlatformAdmin.request.UpdatePlatformAdminRequest
import com.expert.operrwork.data.model.updatePlatformAdmin.response.UpdatePlatformAdminResponse
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by Akshay.
 */
interface RetrofitInterface {
    @POST("api/admin/auth/login")
    fun getLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("api/forgot-password/")
    fun forgot(@Body request: ForgotPasswordRequest): Call<ForgotResponse>

    //Get All Company For Drop Down
    @GET("api/company/drop_down/")
    fun getAllCompany(): Call<GetAllCompanyDropDownResponse>

    //Get All  Admin For Drop Down
    @GET("api/admin/drop_down")
    fun getAdminDropDown(): Call<GetAdminDropDown>

    //All Created
    @GET("api/admin/admin_drop_down/")
    fun adminCreatedDropDown(): Call<AdminCreatedDropDown>

    //All Admin for Listing
    @GET("api/admin/agency_admin/")
    fun getAllAgencyAdmin(
        @Query("companyId") companyId: String,
        @Query("agencyId") agencyId: String,
        @Query("status") status: String,
        @Query("createdBy") createdBy: String,
        @Query("page") page: Int,
        @Query("size") size: String
    ): Call<GetAllAgencyAdminResponse>

    //Get Admin By Id
    @GET("api/admin/{id}")
    fun getAdminById(@Path("id") id: String): Call<GetAdminById>

    //Get Agency Admin By Id
    @GET("api/admin/{id}")
    fun getAgencyAdminById(@Path("id") id: String): Call<AgencyAdminDetailById>


    //Create  Admin
    @POST("api/admin")
    fun createAdmin(@Body createAdminRequest: CreateAdminRequest):
            Call<UpdateAdminResponse>


    //Create Platform  Admin
    @POST("api/admin")
    fun createPlatformAdmin(@Body createPlatformAdminRequest: CreatePlatformAdminRequest): Call<CreatePlatformAdminResponse>

    //Update Platform  Admin
    @PUT("api/admin")
    fun updatePlatformAdmin(@Body updatePlatformAdminRequest: UpdatePlatformAdminRequest): Call<UpdatePlatformAdminResponse>


    //get created by company For Company Admin Fragment Created By Drop Down
    @GET("api/company/admin_drop_down/")
    fun getCreatedCompanyForAdmin(): Call<CompanyCreatedResponse>

    //Get All Plateform Admin
    @GET("api/admin/platform_admin")
    fun getAllPlateFormAdmin(): Call<AllPlateFormAdmin>

    //Get all userProcess with filter
    @GET("api/process-monitor/filter")
    fun allUserProcessWithFilter(
        @Query("agencyId") agencyId: String,
        @Query("status") status: String,
        @Query("duration") duration: String,
        @Query("startedBy") startedBy: String,
        @Query("fromDate") fromDate: String,
        @Query("toDate") toDate: String,
        @Query("page") page: Int,
        @Query("size") pageSize: Int
    ): Call<ProcessMonitorResponse>

    //get list of all companies  For Company Admin Fragment
    @GET("api/admin/company_admin/")
    fun getAllCompanyForAdmin(
        @Query("companyId") companyId: String,
        @Query("createdBy") createdBy: String,
        @Query("searchKey") searchKey: String,
        @Query("status") status: String
    ): Call<CompanyAdminResponse>


    @GET("api/agency/byCompId?")
    fun getAgencyByCompId(@Query("companyId") companyId: String): Call<GetAgencyByCompId>


    //Get All  Admin
    @GET("api/admin")
    fun getAllAdmin(): Call<GetAllAdminResponse>

    //Get Bring Employee by comany Id
    @GET("api/employee/drop_down?")
    fun getEmployeeDropDown(@Query("companyid") companyId: String):
            Call<EmployeeDropDownResponse>

    @GET("api/group")
    fun getGroups(
        @Query("companyid") companyId: String,
        @Query("agencyId") agencyId: String
    ): Call<GetAllGroupResponse>

    //Add Group
    @POST("api/group/")
    fun addGroup(@Body addGroupRequest: AddUpdateGroupRequest):Call<AddUpdateGroupResponse>

    //Update Group
    @PUT("api/group/")
    fun updateGroup(@Body addGroupRequest: AddUpdateGroupRequest):Call<AddUpdateGroupResponse>

    //Get Process Monitor By Id
    @GET("api/process-monitor/{id}")
    fun getProcessMonitorById(@Path("id") id: String): Call<GetUserProcessResponse>


    //Get Company By Id
    @GET("api/admin/{id}")
    fun getCompanyById(@Path("id") id: String): Call<GetCompanyAdminById>

    //Get Group By Id
    @GET("api/group/{id}")
    fun getGroupById(@Path("id") id: String): Call<GetGroupDetailById>


    //Create Upadte  Agency Admin
    @PUT("api/admin")
    fun updateAgencyAdmin(@Body updateAdminRequest: UpdateAdminRequest): Call<UpdateAdminResponse>

    //Get Login Name by comany Id and Type
    @GET("api/admin/drop_down?")
    fun getLoginNameDropDown(@Query("companyId") companyId: String,
                             @Query("type") type: String):
            Call<LoginName> //Get Login Name by comany Id and Type

    //Get Agency DropDown by comany Id
    @GET("api/admin/drop_down?")
    fun getAgencyByCompanyIdDropDown(@Query("companyid") companyId: String):
            Call<GetAdminDropDown>

    //SETTINGS UPDATE  Agency Admin
    @PUT("api/admin/access_control_setting")
    fun updateSettings(@Body settingsRequest: SettingsRequest): Call<SettingsResponse>


    //Get All Role
    @GET("api/role")
    fun getAllRole(): Call<GetRoleResponse>

    //Get Agency Admin Role
    @GET("api/role/agency_admin")
    fun getAgencyAdminRole(): Call<GetRoleResponse>

    //Get Company Admin Role
    @GET("api/role/company_admin")
    fun getCompanyAdminRole(): Call<GetRoleResponse>

    //Get Platform Admin Role
    @GET("api/role/platform_admin")
    fun getPlatformAdminRole(): Call<GetRoleResponse>


}