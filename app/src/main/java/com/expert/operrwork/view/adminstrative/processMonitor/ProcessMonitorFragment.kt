package com.expert.operrwork.view.adminstrative.processMonitor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.model.response.processMonitor.Content
import com.expert.operrwork.data.model.response.processMonitor.ProcessMonitorResponse
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import kotlinx.android.synthetic.main.fragment_process_monitor.*
import android.view.ViewTreeObserver
import androidx.core.text.HtmlCompat


class ProcessMonitorFragment() : BaseFragment(), ProcessMonitorAdapter.ItemClick,
    View.OnClickListener {


    private lateinit var mAdapter: ProcessMonitorAdapter
    private lateinit var processMonitorResponse: ProcessMonitorResponse
    private var processMonitorViewModel: ProcessMonitorViewModel? = null
    private var mAllProcessMonitorResponse = mutableListOf<Content>()
    var itemClick: ProcessMonitorAdapter.ItemClick

    var allCompanyModel = GetAllCompanyDropDownResponse()
    var getAgencyDropDown = GetAgencyByCompId()

    var agencyId = ""
    var selectedCompanyId = ""
    var status = ""
    var duration = ""
    var startedBy = ""
    var fromdate = ""
    var toDate = ""
    var mPage = 0
    var mPageSize = 15

    private val COMPANY = "company"
    private val AGENCY = "agency"
    private val STARTED = "started"
    private val STATUS = "status"

    var mCurentPage = 0
    var mLastPage = false

    lateinit var type: String
    var listOfStatus = mutableListOf<String>()


    init {
        itemClick = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_process_monitor, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processMonitorViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!))
                .get(ProcessMonitorViewModel::class.java)
        processMonitorViewModel?.allUserProcessWithFilter(
            agencyId,
            status,
            duration,
            startedBy,
            fromdate,
            toDate,
            mPage,
            mPageSize
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listner()
        tvPath.text = HtmlCompat.fromHtml(
            getString(com.expert.operrwork.R.string.tb_home) + " > " +
                    getString(com.expert.operrwork.R.string.side_menu_Adminstrative) + " > " +
                    "<font color=black>" + getString(com.expert.operrwork.R.string.adminstrative_process_monitor)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )

    }


    private fun scrollListner() {
        scrollView.viewTreeObserver
            .addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
                if (scrollView == null)
                    return@OnScrollChangedListener
                val view = scrollView.getChildAt(scrollView.getChildCount() - 1) as View

                val diff = view.bottom - (scrollView.height + scrollView.scrollY)
                if (diff == 0) {
                    if (!mLastPage && mCurentPage == mPage) {
                        mPage = mPage + 1
                        processMonitorViewModel?.allUserProcessWithFilter(
                            agencyId,
                            status,
                            duration,
                            startedBy,
                            fromdate,
                            toDate,
                            mPage,
                            mPageSize
                        )
                    }
                }
            })
    }

    private fun listner() {
        ll_company.setOnClickListener(this)
        ll_agency.setOnClickListener(this)
        tvCreated.setOnClickListener(this)
        tvStatus.setOnClickListener(this)
        llFromDate.setOnClickListener(this)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter(mAllProcessMonitorResponse)
        initObserver()
        scrollListner()

    }


    private fun initObserver() {
        processMonitorViewModel!!.getAllUserProcessWithFilter().observe(this, Observer {
            if (it != null && it.status.equals("SUCCESS")) {
                processMonitorResponse = it
                mCurentPage = mPage
                mLastPage = processMonitorResponse.data?.last!!
                mAllProcessMonitorResponse.addAll(processMonitorResponse.data?.content!!)
                mAdapter.customNotify(mAllProcessMonitorResponse)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })


        processMonitorViewModel!!.getCompanyDropDown().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                allCompanyModel = it
                var listOfCompanyName = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfCompanyName.add(item.name!!)
                }
                intilizeAdapter(context!!, listOfCompanyName, tvCompany)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        processMonitorViewModel!!.getAgencyDropDown().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                getAgencyDropDown = it
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
        data: MutableList<Content>
    ) {

        rv_process_monitor.layoutManager = LinearLayoutManager(activity)
        mAdapter = ProcessMonitorAdapter(data, processMonitorViewModel!!, itemClick)
        rv_process_monitor.isNestedScrollingEnabled = false
        rv_process_monitor?.adapter = mAdapter
    }

    override fun onItemClick(position: Int) {

        addReplaceFragment(
            R.id.fl_adminstrative,
            ProcessMonitorDetailFragment(mAllProcessMonitorResponse?.get(position).id),
            false,
            true,
            null
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_agency -> {
                if (selectedCompanyId.isEmpty()) {
                    showError(getString(R.string.select_company_first))
                } else {
                    type = AGENCY
                    processMonitorViewModel?.getAgencyByCompanyIdDropDown(selectedCompanyId)
                }

            }
            R.id.ll_company -> {
                type = COMPANY
                processMonitorViewModel?.getComapnyListDropDown()
            }
            R.id.tvCreated -> {
                type = STARTED

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

    override fun dropDownItemClick(position: Int) {
        if (type.equals(COMPANY)) {
            tvCompany.setText(allCompanyModel.data?.get(position)?.name)
            selectedCompanyId = allCompanyModel.data?.get(position)?.id.toString()
            mPopupWindow.dismiss()
            mPage = 0
            processMonitorViewModel?.allUserProcessWithFilter(
                agencyId,
                status,
                duration,
                startedBy,
                fromdate,
                toDate,
                mPage,
                mPageSize
            )

        } else if (type.equals(AGENCY)) {
            tvAgency.setText(getAgencyDropDown.data?.get(position)?.name)
            agencyId = getAgencyDropDown.data?.get(position)?.id.toString()
            mPopupWindow.dismiss()
            mPage = 0
            processMonitorViewModel?.allUserProcessWithFilter(
                agencyId,
                status,
                duration,
                startedBy,
                fromdate,
                toDate,
                mPage,
                mPageSize
            )

        } else if (type.equals(STARTED)) {


        } else if (type.equals(STATUS)) {
            tvStatus.setText(listOfStatus.get(position))
            if (listOfStatus.equals("Active")) {
                status = "1"
            } else {
                status = "0"
            }
            mPopupWindow.dismiss()
            mPage = 0
            processMonitorViewModel?.allUserProcessWithFilter(
                agencyId,
                status,
                duration,
                startedBy,
                fromdate,
                toDate,
                mPage,
                mPageSize
            )

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()

    }

    private fun unregisterObserver() {
        processMonitorViewModel!!.getAllUserProcessWithFilter().removeObservers(this)
        processMonitorViewModel!!.getCompanyDropDown().removeObservers(this)
        processMonitorViewModel!!.getAgencyDropDown().removeObservers(this)
    }
}
