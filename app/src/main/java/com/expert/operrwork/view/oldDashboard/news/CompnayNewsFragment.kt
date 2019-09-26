package com.expert.operrwork.view.oldDashboard.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment

/**
 * Created by Akshay.
 */
class CompnayNewsFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_company_news, container, false)
    }
}