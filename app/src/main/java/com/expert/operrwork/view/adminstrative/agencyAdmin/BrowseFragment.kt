package com.expert.operrwork.view.adminstrative.agencyAdmin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.response.adminCreatedDropDown.AdminCreatedDropDown
import com.expert.operrwork.data.model.response.getAllAgencyAdmin.Content
import com.expert.operrwork.data.model.response.getAllAgencyAdmin.GetAllAgencyAdminResponse
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.util.SharedPref
import com.expert.operrwork.util.helper.AppUtils.AppUtils.addTab
import com.expert.operrwork.util.helper.AppUtils.AppUtils.arrayListTitle
import com.expert.operrwork.util.helper.AppUtils.AppUtils.viewPager
import kotlinx.android.synthetic.main.fragment_admin_browse.*
import org.koin.android.ext.android.inject


/**
 * Created by Akshay.
 */
class BrowseFragment : BaseFragment(),
    AgencyAdminsAdapter.ItemClick,
    View.OnClickListener {

    lateinit var  layoutManager: LinearLayoutManager
    private val COMPANY = "company"
    private val AGENCY = "agency"
    private val CREATED = "created"
    private val STATUS = "status"
    lateinit var mAdapter: AgencyAdminsAdapter
    private var agencyViewModel: AgencyViewModel? = null
    private var getAllAgencyAdminList = mutableListOf<Content>()
    val sharedPref: SharedPref by inject()
    var getAllAgencyAdminResponse = GetAllAgencyAdminResponse()
    var adminDropDown = AdminCreatedDropDown()
    var allCompanyModel = GetAllCompanyDropDownResponse()
    var agencyDropDown = GetAgencyByCompId()
    val itemClick: AgencyAdminsAdapter.ItemClick
    var selectedCompanyId = ""
    var selectedAgencyId = ""
    var selectedStatusId = ""
    var selectedCreatedById = ""
    var mPage = 0
    var mPageSize = "15"
    var mCurentPage = 0
    var mLastPage= false
    lateinit var type: String
    var listOfStatus = mutableListOf<String>()
    var data: MutableList<Content> = mutableListOf<Content>()


    init {
        itemClick = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin_browse, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        agencyViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(AgencyViewModel::class.java)

        agencyViewModel?.getAllAgencyAdmin(selectedCompanyId, selectedAgencyId, selectedCreatedById, selectedStatusId,mPage,mPageSize)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter(data)
        setRecyclerViewScrollListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        listner()
        initObserver()

        // setAdapter(getAllAgencyAdminList)
        tvPath.text = HtmlCompat.fromHtml(
            getString(R.string.tb_home) + " > " +
                    getString(R.string.side_menu_Adminstrative) + " > " +
                    getString(R.string.adminstrative_agency_admin) + " > " +
                    "<font color=black>" + getString(R.string.browse_small)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )

    }

    private fun listner() {
        ll_company.setOnClickListener(this)
        ll_agency.setOnClickListener(this)
        ll_created.setOnClickListener(this)
        ll_status.setOnClickListener(this)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun init() {
    }

    private fun initObserver() {
        agencyViewModel!!.getCompanyDropDown().observe(this, Observer {
            if (it != null && it.status.equals("SUCCESS")) {
                allCompanyModel = it
                var listOfCompanyName = mutableListOf<String>()
                for (item in it.data!!) {
                    listOfCompanyName.add(item.name!!)
                }
                intilizeAdapter(context!!, listOfCompanyName, tvCompany)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        agencyViewModel!!.getAllAgencyAdminResponse().observe(this, Observer {
            if (it != null && it.status.equals("SUCCESS")) {
                getAllAgencyAdminResponse = it
                mCurentPage = mPage
                if (mCurentPage==0)
                    data.clear()
                mLastPage = getAllAgencyAdminResponse.data?.last!!
                for (item in getAllAgencyAdminResponse.data?.content!!){
                    data.add(item)
                }
                mAdapter.customNotify(data)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        agencyViewModel!!.getCreatedByDropDown().observe(this,
            Observer {
                if (it != null && it.status.equals("SUCCESS")) {
                    adminDropDown = it
                    var listOfCreatedName = mutableListOf<String>()
                    for (item in it.data!!) {
                        listOfCreatedName.add(item.name!!)
                    }
                    intilizeAdapter(context!!, listOfCreatedName, tvCreated)
                } else {
                    showError(getString(R.string.str_something_wrong))
                }
            })

        agencyViewModel!!.getAgencyDropDownById().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                agencyDropDown = it
                var listOfAdminName = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfAdminName.add(item.name!!)
                }
                intilizeAdapter(context!!, listOfAdminName, tvAgency)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
    }


    fun setAdapter(
        data: List<Content>?
    ) {

        layoutManager= LinearLayoutManager(activity)
        rv_agency_admin.layoutManager = layoutManager
        mAdapter = AgencyAdminsAdapter(data, agencyViewModel!!, itemClick)
        rv_agency_admin?.adapter = mAdapter
    }

    fun setRecyclerViewScrollListener() {
        rv_agency_admin.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                    val totalItemCount = recyclerView.layoutManager!!.itemCount

                    //Pagination
                    if (dy > 0) {
                        if ( !mLastPage && mCurentPage == mPage && (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            mPage = mPage + 1
                            agencyViewModel?.getAllAgencyAdmin(selectedCompanyId, selectedAgencyId, selectedCreatedById, selectedStatusId,mPage,mPageSize)
                        }
                    }

                }

            }
        })
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_agency -> {
                if (selectedCompanyId.isEmpty()) {
                    showError(getString(R.string.select_company_first))
                } else {
                    type = AGENCY
                    agencyViewModel?.getAgencyByCompanyIdDropDown(selectedCompanyId)
                }


            }
            R.id.ll_company -> {
                type = COMPANY
                agencyViewModel?.getComapnyListDropDown()
            }
            R.id.ll_created -> {
                type = CREATED
                agencyViewModel?.adminCreatedDropDown()
            }
            R.id.ll_status -> {
                type = STATUS
                listOfStatus = mutableListOf<String>()
                listOfStatus.add("Active")
                listOfStatus.add("Inactive")
                intilizeAdapter(context!!, listOfStatus, tvStatus)
            }
        }

    }


    override fun onItemClick(position: Int) {

        val bundle = Bundle()
        bundle.putString("adminId", getAllAgencyAdminResponse.data?.content?.get(position)?.id.toString())

        val adminDetailFragment = AgencyAdminDetailFragment()
        adminDetailFragment.arguments = bundle

        addTab(resources.getString(R.string.str_agency_admin_details), adminDetailFragment)
        viewPager.setCurrentItem(arrayListTitle.size - 1, true)
    }

    override fun dropDownItemClick(position: Int) {
        if (type.equals(COMPANY)) {
            tvCompany.text = allCompanyModel.data?.get(position)?.name
            selectedCompanyId = allCompanyModel.data?.get(position)?.id.toString()
            mPopupWindow.dismiss()
            mPage=0
            agencyViewModel?.getAllAgencyAdmin(
                selectedCompanyId,
                selectedAgencyId,
                selectedCreatedById,
                selectedStatusId,
                mPage,mPageSize
            )

        } else if (type.equals(AGENCY)) {
            tvAgency.text = agencyDropDown.data?.get(position)?.name
            selectedAgencyId = agencyDropDown.data?.get(position)?.id.toString()
            mPopupWindow.dismiss()
            mPage=0
            agencyViewModel?.getAllAgencyAdmin(
                selectedCompanyId,
                selectedAgencyId,
                selectedCreatedById,
                selectedStatusId,
                mPage,mPageSize
            )

        } else if (type.equals(CREATED)) {
            tvCreated.text = adminDropDown.data?.get(position)?.name
            selectedCreatedById = adminDropDown.data?.get(position)?.id.toString()
            mPopupWindow.dismiss()
            mPage=0
            agencyViewModel?.getAllAgencyAdmin(
                selectedCompanyId,
                selectedAgencyId,
                selectedCreatedById,
                selectedStatusId,
                mPage,mPageSize
            )

        } else if (type.equals(STATUS)) {
            tvStatus.text = listOfStatus.get(position)
            if (listOfStatus.equals("Active")) {
                selectedStatusId = "1"
            } else {
                selectedStatusId = "0"
            }
            mPopupWindow.dismiss()
            mPage=0
            agencyViewModel?.getAllAgencyAdmin(
                selectedCompanyId,
                selectedAgencyId,
                selectedCreatedById,
                selectedStatusId,
                mPage,mPageSize
            )

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()
    }

    private fun unregisterObserver() {
        agencyViewModel!!.getAllAgencyAdminResponse().removeObservers(this)
        agencyViewModel!!.getCreatedByDropDown().removeObservers(this)
        agencyViewModel!!.GetAgencyDropDown().removeObservers(this)
        agencyViewModel!!.getCompanyDropDown().removeObservers(this)
    }


}