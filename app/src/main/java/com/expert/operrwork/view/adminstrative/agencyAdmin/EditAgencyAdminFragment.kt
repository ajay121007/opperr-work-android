package com.expert.operrwork.view.adminstrative.agencyAdmin

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.admin.updateAdminRequest.Agency
import com.expert.operrwork.data.model.admin.updateAdminRequest.Role
import com.expert.operrwork.data.model.admin.updateAdminRequest.UpdateAdminRequest
import com.expert.operrwork.data.model.agencyAdminById.AgencyAdminDetailById
import com.expert.operrwork.data.model.group.getAllGroup.GetAllGroupResponse
import com.expert.operrwork.data.model.menu.MenuItem
import com.expert.operrwork.data.model.response.bringEmployee.EmployeeDropDownResponse
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.model.role.GetRoleResponse
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.databinding.FragmentAgencyEditBinding
import com.expert.operrwork.view.adminstrative.MenuAdapter
import com.expert.operrwork.view.adminstrative.MenuDialogAdapter
import kotlinx.android.synthetic.main.dialog_menu_list.*
import kotlinx.android.synthetic.main.fragment_agency_edit.*

class EditAgencyAdminFragment : BaseFragment(), View.OnClickListener {

    private var selectedStaus= "0"
    private lateinit var agencyAdminDetailById: AgencyAdminDetailById
    lateinit var binding: FragmentAgencyEditBinding
    lateinit var agencyViewModel: AgencyViewModel
    var allCompanyModel = GetAllCompanyDropDownResponse()
    var GetAgencyDropDown = GetAgencyByCompId()
    var menuList: ArrayList<Long> = ArrayList()
    var selectedCompanyId = ""
    var selectedAgencyId = ""
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menuItem: MenuItem
    private lateinit var mMenuDialog: Dialog
    lateinit var type: String
    var allEmployeesModel = EmployeeDropDownResponse()
    var selectedEmployeeId = ""
    var roleLevel = ""
    var roleID = ""
    private lateinit var mGroupDialog: Dialog
    private var groupsModel: GetAllGroupResponse? = null
    private var groupList: ArrayList<Long> = ArrayList()
    var getRole = GetRoleResponse()


    private val COMPANY = "company"
    private val AGENCY = "agency"
    private val EMPLOYEE = "employee"
    private val ADMIN = "admin"
    private val GROUP = "group"
    private val GENDER = "gender"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        agencyViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(AgencyViewModel::class.java)
        initObserver()
        agencyViewModel.getAgencyAdminById(arguments?.getString("adminId")!!)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agency_edit, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listener()
        getMenuItems()
        setMenuAdapter()

        tvPath.text = HtmlCompat.fromHtml(
            getString(R.string.tb_home) + " > " +
                    getString(R.string.side_menu_Adminstrative) + " > " +
                    getString(R.string.adminstrative_agency_admin) + " > " +
                    "<font color=black>" + getString(R.string.edit)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )


    }

    private fun listener() {
        tvAddAnother.setOnClickListener(this)
        btn_save.setOnClickListener(this)
        tvAgency.setOnClickListener(this)
        tvCompany.setOnClickListener(this)
        llBringEmployee.setOnClickListener(this)
        tvGender.setOnClickListener(this)
        llGroup.setOnClickListener(this)
        llAdminType.setOnClickListener(this)


        switchStatus.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                selectedStaus = "1"
                textStatus.text = "ACTIVE"
            } else {
                selectedStaus = "0"
                textStatus.text = "INACTIVE"

            }
        }

    }

    private fun setMenuAdapter() {
        rv_menus.setLayoutManager(LinearLayoutManager(activity))
        menuAdapter = MenuAdapter(menuItem.menu_list!!)
        rv_menus.adapter = menuAdapter
        rv_menus.isNestedScrollingEnabled = false

    }

    private fun getMenuItems() {
        val menuString = resources.openRawResource(R.raw.menu_list)
            .bufferedReader().use { it.readText() }

        menuItem = Gson().fromJson(menuString, MenuItem::class.java)
    }

    private fun showMenuDialog() {

        mMenuDialog = Dialog(activity)
        mMenuDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mMenuDialog.setCancelable(false)
        mMenuDialog.setContentView(R.layout.dialog_menu_list)

        val menuDialogAdapter = MenuDialogAdapter(
            menuItem.menu_list!!
        )

        mMenuDialog.recyclerView.setLayoutManager(LinearLayoutManager(activity))

        mMenuDialog.recyclerView.setAdapter(menuDialogAdapter)

        mMenuDialog.btn_ok.setOnClickListener(View.OnClickListener {
            for (i in 0 until menuItem.menu_list!!.size - 1) {
                if (menuItem.menu_list!!.get(i).checked) {
                    if (!menuList.contains(menuItem.menu_list!!.get(i).id?.toLong())) {
                        menuList.add(menuItem.menu_list!!.get(i).id?.toLong()!!)
                    }
                    Log.d("checkedName", menuItem.menu_list!!.get(i).name)
                }
            }
            menuAdapter.notifyDataSetChanged()
            mMenuDialog.dismiss()
        })

        mMenuDialog.show()
    }


    private fun filterMenuList() {
        for (i in 0..menuItem.menu_list?.size!! - 1) {
            for (j in 0..agencyAdminDetailById.data?.menuList?.size!! - 1) {
                if (agencyAdminDetailById.data?.menuList!!.get(j).equals(menuItem.menu_list!!.get(i).id)) {
                    menuItem.menu_list!!.get(i).checked = true
                    if (!menuList.contains(agencyAdminDetailById.data?.menuList!!.get(j)?.toLong())) {
                        menuList.add(agencyAdminDetailById.data?.menuList!!.get(j)?.toLong()!!)
                    }
                }
            }
            menuAdapter.notifyDataSetChanged()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvAddAnother -> {
                showMenuDialog()
            }
            R.id.btn_save -> {
                mBaseActivity?.hideKeyboard(activity!!)
                if (edt_first_name.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_first_name))
                } else if (edt_last_name.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_last_name))
                } else if (edt_email.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_email))
                } else if (!mBaseActivity!!.isValidEmail(edt_email.text.toString())) {
                    showError(getString(R.string.error_valid_email))
                } else if (tvGender.text.toString().isEmpty()) {
                    showError(getString(R.string.select_gender))
                } else if (edt_address_one.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_address))
                } else if (tvAdminType.text.toString().isEmpty()) {
                    showError(getString(R.string.select_admin_type))
                } else if (edt_user_name.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_user_name))
                }  else {
                    updateAdminRequest()
                }


            }
            R.id.llAdminType -> {
                type = ADMIN
                agencyViewModel?.getAgencyAdminRole()
            }
            R.id.tvAgency -> {
                type = AGENCY
                agencyViewModel?.getAgencyByCompanyIdDropDown(selectedCompanyId)

            }
            R.id.tvCompany -> {
                type = COMPANY
                agencyViewModel?.getComapnyListDropDown()
            }
            R.id.llBringEmployee -> {
                type = EMPLOYEE
                agencyViewModel?.getEmployeeDropDown(selectedCompanyId)
            }
            R.id.llGroup -> {
                type = GROUP

                if (selectedCompanyId.isEmpty())
                    showError(resources.getString(R.string.please_select_company))
                else if (selectedAgencyId.isEmpty())
                    showError(resources.getString(R.string.please_select_agency))
                else
                    agencyViewModel?.getAllGroupsRequest(selectedCompanyId, selectedAgencyId)
            }
            R.id.tvGender -> {
                type = GENDER
                intilizeAdapter(context!!, getGenderArrayList(), tvGender)
            }
        }
    }

    private fun updateAdminRequest() {
        var updateAgencyAdminRequest = UpdateAdminRequest()
        var role = Role()
        var agency=Agency()

        updateAgencyAdminRequest.firstName = edt_first_name.text.toString()
        updateAgencyAdminRequest.id = arguments?.getString("adminId")?.toLong()
        updateAgencyAdminRequest.middleName = edt_middle_name.text.toString()
        updateAgencyAdminRequest.lastName = edt_last_name.text.toString()
        updateAgencyAdminRequest.email = edt_email.text.toString()
        updateAgencyAdminRequest.phone = edt_phone.text.toString()
        updateAgencyAdminRequest.secondaryPhone = edt_secondary_phone.text.toString()
        updateAgencyAdminRequest.username = edt_user_name.text.toString()
        updateAgencyAdminRequest.did = edt_did.text.toString()
        updateAgencyAdminRequest.status = Integer.parseInt(selectedStaus)
        updateAgencyAdminRequest.gender = tvGender.text.toString()

        agency.id = selectedAgencyId.toLong()
        agency.updatedAt = agencyAdminDetailById?.data?.agency?.updatedAt
        agency.createdAt = agencyAdminDetailById?.data?.agency?.createdAt
        agency.addressOne = edt_address_one.text.toString()
        agency.addressTwo = edt_address_two.text.toString()
        agency.city = edt_address_city.text.toString()
        agency.state = edt_state.text.toString()
        agency.zipcode = edt_address_zip.text.toString()

        updateAgencyAdminRequest.menuList = menuList
        updateAgencyAdminRequest.deviceAddress = getDeviceId()
        updateAgencyAdminRequest.fcmToken = getDeviceId()
        updateAgencyAdminRequest.rememberToken = getDeviceId()
        updateAgencyAdminRequest.isChangeStatusNotification=false
        updateAgencyAdminRequest.agency=agency

        updateAgencyAdminRequest.menuList = menuList
        updateAgencyAdminRequest.listGroupIds = groupList

        role.level = roleLevel.toLong()
        role.levelName = tvAdminType.text.toString()
        role.id = roleID.toLong()
        role.createdAt = System.currentTimeMillis()
        role.updatedAt = System.currentTimeMillis()

        updateAgencyAdminRequest.role=role
        agencyViewModel?.editAgency(updateAgencyAdminRequest)

    }

    override fun dropDownItemClick(position: Int) {
        when {
            type.equals(COMPANY) -> {
                tvCompany.setText(allCompanyModel.data?.get(position)?.name)
                selectedCompanyId = allCompanyModel.data?.get(position)?.id.toString()
                tvAgency.setText(resources.getString(R.string.select_agency))
                mPopupWindow.dismiss()
            }

            type.equals(AGENCY) -> {
                tvAgency.setText(GetAgencyDropDown.data?.get(position)?.name)
                selectedAgencyId = GetAgencyDropDown.data?.get(position)?.id.toString()
                mPopupWindow.dismiss()
            }

            type.equals(GENDER) -> {
                tvGender.setText(getGenderArrayList().get(position))
                mPopupWindow.dismiss()
            }
            type.equals(EMPLOYEE) -> {
                tvBringEmployee.setText(allEmployeesModel.data?.get(position)?.username)
                selectedEmployeeId = allEmployeesModel.data?.get(position)?.id.toString()
                edt_first_name.setText(allEmployeesModel.data?.get(position)?.firstName)
                edt_last_name.setText(allEmployeesModel.data?.get(position)?.lastName)
                edt_email.setText(allEmployeesModel.data?.get(position)?.email)
                edt_phone.setText(allEmployeesModel.data?.get(position)?.phone)
                edt_address_zip.setText(allEmployeesModel.data?.get(position)?.pin)

                mPopupWindow.dismiss()
            }
            type.equals(ADMIN) -> {

                roleLevel = getRole?.data?.get(position)?.level.toString()
                roleID = getRole?.data?.get(position)?.id.toString()
                tvAdminType.text = getRole?.data?.get(position)?.levelName
                mPopupWindow.dismiss()
            }
        }

    }

    private fun initObserver() {

        agencyViewModel!!.getAgencyDropDownById().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                GetAgencyDropDown = it
                var listOfAdminName = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfAdminName.add(item.name!!)
                }
                intilizeAdapter(context!!, listOfAdminName, tvAgency)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        agencyViewModel!!.getRoleResponse().observe(this, Observer {
            if (it?.status.equals("SUCCESS")) {
                getRole=it
                var listOfAdmin = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfAdmin.add(item.levelName!!)
                }
                intilizeAdapter(context!!, listOfAdmin, tvAdminType)

            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })


        agencyViewModel!!.getCompanyDropDown().observe(this, Observer {
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


        agencyViewModel!!.getAgencyAdminById().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                binding.model = it?.data
                selectedCompanyId = it?.data?.companyId.toString()
                agencyAdminDetailById = it
                selectedAgencyId=it?.data?.agency?.id.toString()
                selectedCompanyId=it?.data?.company?.id.toString()
                roleLevel=it?.data?.role?.level.toString()
                roleID=it?.data?.role?.id.toString()
                filterMenuList()

                if (it?.data?.status?.equals("1")!!) {
                    textStatus.setText("Active")
                    switchStatus.isChecked = true
                } else {
                    textStatus.setText("Inactive")
                    switchStatus.isChecked = false
                }



            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        agencyViewModel!!.getEmployeeDropDownResponse().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                allEmployeesModel = it
                var listOfEmployeeName = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfEmployeeName.add(item?.username!!)
                }
                intilizeAdapter(context!!, listOfEmployeeName, tvBringEmployee)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        agencyViewModel!!.getAllGroups().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                groupsModel = it
                if(it?.data?.size!!>0){
                    showGroupDialog()
                }else{
                    showError(getString(R.string.no_group))
                }

            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        agencyViewModel!!.getUpdateAgencyAdmin().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
              showError(it?.message!!)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
    }

    private fun unregisterObserver() {
        agencyViewModel!!.GetAgencyDropDown().removeObservers(this)
        agencyViewModel!!.getAgencyAdminById().removeObservers(this)
        agencyViewModel!!.getCompanyDropDown().removeObservers(this)

    }

    private fun showGroupDialog() {
        mGroupDialog = Dialog(activity)
        mGroupDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mGroupDialog.setCancelable(false)
        mGroupDialog.setContentView(R.layout.dialog_menu_list)
        val groupDialogAdapter =
            GroupDialogAdapter(
                groupsModel?.data?.content
            )
        mGroupDialog.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        mGroupDialog.recyclerView.setAdapter(groupDialogAdapter)
        mGroupDialog.btn_ok.setOnClickListener(View.OnClickListener {
            for (i in 0 until groupsModel?.data?.content!!.size - 1) {
                if (groupsModel!!.data!!.content!!.get(i)?.checked!!) {
                    if (!groupList.contains(groupsModel!!.data!!.content!!.get(i)?.id?.toLong())) {
                        groupList.add(groupsModel!!.data!!.content!!.get(i)?.id?.toLong()!!)
                        tvGroup.append(groupsModel!!.data!!.content!!.get(i)?.name)
                    }
                }
            }

            mGroupDialog.dismiss()
        })
        mGroupDialog.show()
    }

}