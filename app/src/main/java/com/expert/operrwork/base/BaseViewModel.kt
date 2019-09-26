package com.expert.operrwork.base

import androidx.lifecycle.ViewModel
import com.expert.operrwork.util.helper.NetworkManager


open class BaseViewModel : ViewModel() {


    var mBaseProgressDialogFragment: BaseProgressDialogFragment? = null

    fun onProgressShow(activity: BaseActivity) {
        //Internet connection checking
        if (!NetworkManager(activity).checkInternetConnection()) {activity.showError("Please Check Your Internet Connection")}

        mBaseProgressDialogFragment = BaseProgressDialogFragment().getInstance()
        if (!mBaseProgressDialogFragment!!.isVisible) {
            mBaseProgressDialogFragment!!.show(activity.supportFragmentManager, "ProgressDialog")
        }
    }

    fun onProgressHide() {
        if (mBaseProgressDialogFragment != null) {
            mBaseProgressDialogFragment!!.dismiss()
        } else {
            return
        }
    }


}