package com.expert.operrwork.view.adminstrative.companyAdmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.local.PreferenceManager
import com.expert.operrwork.data.model.response.companyAdmin.CompanyAdminResponse
import com.expert.operrwork.data.model.response.companyAdmin.CompanyCreatedResponse
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.util.helper.AppUtils
import com.expert.operrwork.util.helper.AppUtils.AppUtils.arrayListTitle
import kotlinx.android.synthetic.main.fragment_show_company_admin.*


/**
 * Created by Akshay.
 */
class ShowCompanyAdminFragment : BaseFragment(), View.OnClickListener, CompanyAdminAdapter.ItemClick {

    private lateinit var mAdapter: CompanyAdminAdapter
    private lateinit var createdDropDown: CompanyCreatedResponse
    private lateinit var companyDropDownModel: GetAllCompanyDropDownResponse
    private lateinit var getAllCompaniesModel: CompanyAdminResponse

    private var selectedCompanyId = ""
    private var selectedStaus = ""
    private var selectedCreatedBy = ""
    private var searchKey = ""
    private var companyViewModel: CompanyViewModel? = null

    private var arrayListStatus: ArrayList<String> = ArrayList()
    private var compamyAdminList = mutableListOf<CompanyAdminResponse.ContentBean>()

    private lateinit var type: String
    private val COMPANY = "company"
    private val STATUS = "status"
    private val CREATED = "created"
    val itemClick: CompanyAdminAdapter.ItemClick

    init {
        itemClick = this
    }

    var mPreferenceManager: PreferenceManager? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_company_admin, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        companyViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(CompanyViewModel::class.java)
        companyViewModel?.getAllCompaniesRequest(selectedCompanyId,selectedCreatedBy,searchKey,selectedStaus)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter(compamyAdminList)
        listener()
        initObserver()
        tvPath.text = HtmlCompat.fromHtml(getString(R.string.tb_home) +" > " +
                getString(R.string.side_menu_Adminstrative) +" > " +
                getString(R.string.adminstrative_company_admin) +" > " +
                "<font color=black>" + getString(R.string.show)
                + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY)

    }


    private fun listener() {
        ll_company.setOnClickListener(this)
        textViewStatus.setOnClickListener(this)
        tvCreated.setOnClickListener(this)
    }


    private fun initObserver() {

        companyViewModel!!.getAllCompanies().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                getAllCompaniesModel = it
                mAdapter.customNotify(getAllCompaniesModel.data?.content!!)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
        companyViewModel!!.getCompanyDropDown().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                companyDropDownModel = it
                var listOfCompanyName = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfCompanyName.add(item.name!!)
                }
                intilizeAdapter(context!!, listOfCompanyName, tvCompany)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })


        companyViewModel!!.getCreatedDropDown().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                createdDropDown = it
                var listOfCreatedName = mutableListOf<String>()
                for (item in createdDropDown.data?.createdByUsers!!) {
                    listOfCreatedName.add(item.name!!)
                }
                intilizeAdapter(context!!, listOfCreatedName, tvCreated)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
    }

    fun setAdapter(
        data: MutableList<CompanyAdminResponse.ContentBean>
    ) {

        rv_company_admin.layoutManager = LinearLayoutManager(activity)
        mAdapter = CompanyAdminAdapter(data, companyViewModel!!, itemClick)
        rv_company_admin?.adapter = mAdapter
        rv_company_admin.isNestedScrollingEnabled=false
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ll_company -> {
                type = COMPANY
                companyViewModel?.getComapnyListDropDown()

            }
            R.id.textViewStatus -> {
                type = STATUS
                intilizeAdapter(context!!, getStatusArrayList(), textViewStatus)
            }

            R.id.tvCreated -> {
                type = CREATED
                companyViewModel?.getCreatedDropDownList()

            }
        }
    }

    private fun getStatusArrayList(): ArrayList<String> {
        arrayListStatus.clear()
        arrayListStatus.add("ACTIVE")
        arrayListStatus.add("INACTIVE")
        return arrayListStatus
    }

    override fun dropDownItemClick(position: Int) {
        if (type.equals(COMPANY)) {
            tvCreated.text = ""
            tvCompany.text = companyDropDownModel.data?.get(position)?.name
            selectedCompanyId = companyDropDownModel.data?.get(position)?.id.toString()
            mPopupWindow.dismiss()
            companyViewModel?.getAllCompaniesRequest(selectedCompanyId,selectedCreatedBy,searchKey,selectedStaus)


        }
      else if (type.equals(STATUS)) {
            textViewStatus.text = getStatusArrayList().get(position)
            if (getStatusArrayList().get(position).equals("ACTIVE"))
                selectedStaus = "0"
            else
                selectedStaus = "1"
            mPopupWindow.dismiss()
            companyViewModel?.getAllCompaniesRequest(selectedCompanyId,selectedCreatedBy,searchKey,selectedStaus)

        } else if (type.equals(CREATED)) {
            selectedCreatedBy= createdDropDown.data?.createdByUsers?.get(position)?.id!!.toString()
            tvCreated.setText(createdDropDown.data?.createdByUsers?.get(position)?.name)
            mPopupWindow.dismiss()
            companyViewModel?.getAllCompaniesRequest(selectedCompanyId,selectedCreatedBy,searchKey,selectedStaus)

        }
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putString("adminId", getAllCompaniesModel.data?.content?.get(position)?.id.toString())

        val detailCompanyFragment = CompanyDetailFragment()
        detailCompanyFragment.arguments = bundle

        AppUtils.addTab(resources.getString(R.string.str_company_admin_details),
            detailCompanyFragment)

        AppUtils.viewPager.setCurrentItem(arrayListTitle.size - 1,
            true)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()

    }

    private fun unregisterObserver() {
        companyViewModel!!.getAllCompanies().removeObservers(this)
        companyViewModel!!.getCompanyDropDown().removeObservers(this)
        companyViewModel!!.getCreatedDropDown().removeObservers(this)
    }
}