package com.expert.operrwork.data.viewModelBaseContext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.expert.operrwork.base.BaseActivity
import com.expert.operrwork.view.adminstrative.agencyAdmin.AgencyViewModel
import com.expert.operrwork.view.adminstrative.companyAdmin.CompanyViewModel
import com.expert.operrwork.view.adminstrative.groups.GroupViewModel
import com.expert.operrwork.view.adminstrative.platformadmin.PlatformAdminViewModel
import com.expert.operrwork.view.adminstrative.processMonitor.ProcessMonitorViewModel
import com.expert.operrwork.view.userAccess.UserAccessViewModel

/**
 * Created by Akshay.
 */
@Suppress("UNCHECKED_CAST")
class BaseContext(var mBaseActivity: BaseActivity) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(tClass: Class<T>): T {
        return when (tClass) {
            UserAccessViewModel::class.java -> UserAccessViewModel(
                mBaseActivity
            ) as T
            AgencyViewModel::class.java -> AgencyViewModel(
                mBaseActivity
            ) as T
            CompanyViewModel::class.java -> CompanyViewModel(
                mBaseActivity
            ) as T
            PlatformAdminViewModel::class.java -> PlatformAdminViewModel(
                mBaseActivity
            ) as T
            GroupViewModel::class.java -> GroupViewModel(
                mBaseActivity
            )as T
            ProcessMonitorViewModel::class.java -> ProcessMonitorViewModel(
                mBaseActivity
            ) as T
            else -> super.create(tClass)
        }
    }
}