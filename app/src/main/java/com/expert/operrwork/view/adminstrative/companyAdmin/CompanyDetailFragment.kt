package com.expert.operrwork.view.adminstrative.companyAdmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.databinding.FragmentCompanyDetailBinding
import com.expert.operrwork.util.helper.AppUtils
import com.expert.operrwork.util.helper.AppUtils.AppUtils.arrayListTitle
import com.expert.operrwork.util.helper.AppUtils.AppUtils.viewPager
import kotlinx.android.synthetic.main.fragment_company_detail.*

class CompanyDetailFragment:BaseFragment() {

    lateinit var compnayViewModel: CompanyViewModel
    lateinit var binding: FragmentCompanyDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_company_detail,container,false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvPath.text = HtmlCompat.fromHtml(getString(R.string.tb_home) +" > " +
                getString(R.string.side_menu_Adminstrative) +" > " +
                getString(R.string.adminstrative_company_admin) +" > " +
                "<font color=black>" + getString(R.string.detail)
                + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY)

        compnayViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).
                get(CompanyViewModel::class.java)
        initObserver()
        compnayViewModel.getAdminById(arguments?.getString("adminId")!!)
        listener()
    }

    private fun initObserver() {
        compnayViewModel!!.getCompanyDetails().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                binding.model=it?.data
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
    }

    private fun listener() {
        btn_edit.setOnClickListener{
            if (arrayListTitle.size > 4)
                viewPager.setCurrentItem(arrayListTitle.size - 1, true)
            else {

                val bundle = Bundle()
                bundle.putString("adminId", arguments?.getString("adminId"))

                val editCompanyFragment = EditCompanyAdminFragment()
                editCompanyFragment.arguments = bundle

                AppUtils.addTab(resources.getString(R.string.str_company_edit), editCompanyFragment)
                AppUtils.viewPager.setCurrentItem(AppUtils.arrayListTitle.size-1,true)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()

    }

    private fun unregisterObserver() {
        compnayViewModel!!.getCompanyDetails().removeObservers(this)
    }
}