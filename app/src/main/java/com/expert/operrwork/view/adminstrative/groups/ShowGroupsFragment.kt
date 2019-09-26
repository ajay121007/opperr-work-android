package com.expert.operrwork.view.adminstrative.groups

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
import com.expert.operrwork.data.model.group.getAllGroup.ContentItem
import com.expert.operrwork.data.model.group.getAllGroup.GetAllGroupResponse
import com.expert.operrwork.data.model.response.companyAdmin.CompanyAdminResponse
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.util.helper.AppUtils
import com.expert.operrwork.util.helper.AppUtils.AppUtils.arrayListTitle
import kotlinx.android.synthetic.main.fragment_show_groups.*


/**
 * Created by Akshay.
 */
class ShowGroupsFragment : BaseFragment(), View.OnClickListener, GroupsAdapter.ItemClick {

    private lateinit var mAdapter: GroupsAdapter
    private lateinit var agencyDropDown: GetAgencyByCompId
    private lateinit var companyDropDownModel: GetAllCompanyDropDownResponse

    private var selectedCompanyId = ""
    private var selectedAgencyId = ""
    private var groupViewModel: GroupViewModel? = null
    private var groupsModel: GetAllGroupResponse? = null

    private var compamyAdminList = mutableListOf<CompanyAdminResponse.ContentBean>()

    private lateinit var type: String
    private val COMPANY = "company"
    private val AGENCY = "agency"
    val itemClick: GroupsAdapter.ItemClick

    init {
        itemClick = this
    }

    var mPreferenceManager: PreferenceManager? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_groups, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        groupViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(GroupViewModel::class.java)

        groupViewModel?.getAllGroupsRequest(selectedCompanyId,selectedAgencyId)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listener()
        initObserver()

        tvPath.text = HtmlCompat.fromHtml(getString(R.string.tb_home) +" > " +
                getString(R.string.side_menu_Adminstrative) +" > " +
                getString(R.string.adminstrative_groups) +" > " +
                "<font color=black>" + getString(R.string.show)
                + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY)

    }


    private fun listener() {
        ll_group_company.setOnClickListener(this)
        ll_agency.setOnClickListener(this)
        fab.setOnClickListener(this)
    }


    private fun initObserver() {

        groupViewModel!!.getCompanyDropDown().observe(this, Observer {
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


        groupViewModel!!.getAgencyAdminResponse().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                agencyDropDown = it
                var listOfCreatedName = mutableListOf<String>()
                for (item in 0..agencyDropDown.data!!.size-1) {
                    listOfCreatedName.add(agencyDropDown.data!!.get(item).name!!)
                }
                intilizeAdapter(context!!, listOfCreatedName, textViewAgency)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        groupViewModel!!.getAllGroups().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                groupsModel = it
                setAdapter(groupsModel?.data?.content)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
    }

    fun setAdapter(
        data: List<ContentItem?>?
    ) {

        rv_groups.layoutManager = LinearLayoutManager(activity)
        mAdapter = GroupsAdapter(data, groupViewModel!!, itemClick)
        rv_groups?.adapter = mAdapter
        rv_groups.isNestedScrollingEnabled=false
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ll_group_company -> {
                type = COMPANY
                groupViewModel?.getComapnyListDropDown()

            }

            R.id.ll_agency -> {
                if (selectedCompanyId.isEmpty()){
                    showError(getString(R.string.select_company_first))
                }else{
                    type = AGENCY
                    groupViewModel?.getAgencyByCompanyIdDropDown(selectedCompanyId)
                }


            }
            R.id.fab -> {
                AppUtils.viewPager.setCurrentItem(1,true)
            }
        }
    }


    override fun dropDownItemClick(position: Int) {
        if (type.equals(COMPANY)) {
            tvCompany.text = companyDropDownModel.data?.get(position)?.name
            selectedCompanyId = companyDropDownModel.data?.get(position)?.id.toString()
            mPopupWindow.dismiss()

        }
      else if (type.equals(AGENCY)) {
            textViewAgency.text = agencyDropDown.data?.get(position)?.name
            selectedAgencyId = agencyDropDown.data?.get(position)?.id.toString()
            mPopupWindow.dismiss()
            groupViewModel?.getAllGroupsRequest(selectedCompanyId,selectedAgencyId)

        }
    }

    override fun onItemClick(position: Int) {

        val bundle = Bundle()
        bundle.putString("groupId", groupsModel?.data?.content?.get(position)?.id.toString())

        val editGroupFragment = EditGroupFragment()
        editGroupFragment.arguments = bundle

        AppUtils.addTab(resources.getString(R.string.str_group_edit), editGroupFragment)
        AppUtils.viewPager.setCurrentItem(arrayListTitle.size-1,true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()

    }

    private fun unregisterObserver() {
        groupViewModel!!.getCompanyDropDown().removeObservers(this)
        groupViewModel!!.getAgencies().removeObservers(this)
        groupViewModel!!.getAllGroups().removeObservers(this)
    }

}