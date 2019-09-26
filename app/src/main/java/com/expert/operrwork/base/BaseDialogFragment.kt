package com.expert.operrwork.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

/**
 * Created by Akshay.
 */

abstract class BaseDialogFragment : DialogFragment(), BaseView {

    var mBaseProgressDialog: BaseProgressDialogFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun showError(error: String) {
        (activity as BaseActivity).showError(error)
    }

    override fun showConnectionError() {
        (activity as BaseActivity).showConnectionError()
    }

    fun showProgressDialog() {
        if (mBaseProgressDialog == null) {
            mBaseProgressDialog = BaseProgressDialogFragment().getInstance()
        }
        mBaseProgressDialog!!.show(activity!!.supportFragmentManager, "ProgressDialog")
    }

    fun hideProgressDialog() {
        mBaseProgressDialog?.dismiss()
    }

    override fun showLoader() {
        showProgressDialog()
    }

    override fun hideLoader() {
        hideProgressDialog()
    }
}

