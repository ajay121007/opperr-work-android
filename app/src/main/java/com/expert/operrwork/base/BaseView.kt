package com.expert.operrwork.base

interface BaseView {

    fun showLoader()

    fun hideLoader()

    fun showError(error : String)

    fun showConnectionError()

}