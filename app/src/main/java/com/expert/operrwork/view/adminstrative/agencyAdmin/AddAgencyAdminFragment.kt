package com.expert.operrwork.view.adminstrative.agencyAdmin

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.group.getAllGroup.GetAllGroupResponse
import com.expert.operrwork.data.model.menu.MenuItem
import com.expert.operrwork.data.model.createAdmin.Agency
import com.expert.operrwork.data.model.createAdmin.Company
import com.expert.operrwork.data.model.createAdmin.CreateAdminRequest
import com.expert.operrwork.data.model.createAdmin.Role
import com.expert.operrwork.data.model.response.bringEmployee.EmployeeDropDownResponse
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.model.role.GetRoleResponse
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.view.adminstrative.MenuAdapter
import com.expert.operrwork.view.adminstrative.MenuDialogAdapter
import kotlinx.android.synthetic.main.dialog_menu_list.*
import kotlinx.android.synthetic.main.fragment_add_admin.*


/**
 * Created by Akshay.
 */
class AddAgencyAdminFragment : BaseFragment(), View.OnClickListener {

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menuItem: MenuItem
    private lateinit var mMenuDialog: Dialog
    private lateinit var mGroupDialog: Dialog
    var allCompanyModel = GetAllCompanyDropDownResponse()
    var allEmployeesModel = EmployeeDropDownResponse()
    var agencyDropDown = GetAgencyByCompId()
    private var groupsModel: GetAllGroupResponse? = null
    private var groupList: ArrayList<Long> = ArrayList()
    var getRole = GetRoleResponse()

    var selectedCompanyId = ""
    var selectedAgencyId = ""
    var selectedAdminTypeId = ""
    var roleID = ""
    var selectedEmployeeId = ""
    var selectedGender = ""
    var selectedStaus = ""

    var menuList: ArrayList<Long> = ArrayList()


    private val COMPANY = "company"
    private val AGENCY = "agency"
    private val EMPLOYEE = "employee"
    private val ADMIN = "admin"
    private val GROUP = "group"
    private val GENDER = "gender"

    lateinit var type: String

    private var agencyViewModel: AgencyViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_admin, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        agencyViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(AgencyViewModel::class.java)
        initObserver()


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listner()
        getMenuItems()
        setMenuAdapter()

        tvPath.text = HtmlCompat.fromHtml(
            getString(R.string.tb_home) + " > " +
                    getString(R.string.side_menu_Adminstrative) + " > " +
                    getString(R.string.adminstrative_agency_admin) + " > " +
                    "<font color=black>" + getString(R.string.add)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )

    }

    private fun setMenuAdapter() {
        recyclerViewMenu.setLayoutManager(LinearLayoutManager(activity))
        menuAdapter = MenuAdapter(menuItem.menu_list!!)
        recyclerViewMenu.adapter = menuAdapter
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
                if (!menuList.contains(menuItem.menu_list!!.get(i).id?.toLong())) {
                    menuList.add(menuItem.menu_list!!.get(i).id?.toLong()!!)
                }
            }
            menuAdapter.notifyDataSetChanged()
            mMenuDialog.dismiss()
        })

        mMenuDialog.show()
    }

    private fun listner() {
        ll_company.setOnClickListener(this)
        llAgency.setOnClickListener(this)
        llBringEmployee.setOnClickListener(this)
        llAdminType.setOnClickListener(this)
        llGroup.setOnClickListener(this)
        tvGender.setOnClickListener(this)
        tvAddAnother.setOnClickListener(this)
        btn_save.setOnClickListener(this)

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

    private fun initObserver() {

        agencyViewModel!!.getRoleResponse().observe(this, Observer {
            if (it?.status.equals("SUCCESS")) {
                getRole = it
                var listOfAdmin = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfAdmin.add(item.levelName!!)
                }
                intilizeAdapter(context!!, listOfAdmin, tvAdminType)

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
                intilizeAdapter(context!!, listOfAdminName, tvAgencyAdd)
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
                intilizeAdapter(context!!, listOfCompanyName, tvCompanyAdd)
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
                showGroupDialog()
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
        agencyViewModel!!.getAddAgencyAdmin().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                showError(it?.message!!)
            } else {
                if (it.message != null)
                    showError(it.message!!)
                else
                    showError(getString(R.string.str_something_wrong))

            }
        })


    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_company -> {
                type = COMPANY
                agencyViewModel?.getComapnyListDropDown()
            }
            R.id.llAgency -> {
                if (selectedCompanyId.isEmpty())
                    showError(resources.getString(R.string.please_select_company))
                else {
                    type = AGENCY
                    agencyViewModel?.getAgencyByCompanyIdDropDown(selectedCompanyId)
                }

            }
            R.id.llBringEmployee -> {
                type = EMPLOYEE
                agencyViewModel?.getEmployeeDropDown(selectedCompanyId)
            }
            R.id.llAdminType -> {
                type = ADMIN
                agencyViewModel?.getAgencyAdminRole()

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
                } else if (!mBaseActivity!!.isValidPassword(edt_password.text.toString())) {
                    showError(getString(R.string.password_must))
                } else if (!edt_password.text.toString().equals(edt_confirm_password.text.toString())) {
                    showError(getString(R.string.password_did_not_match))
                } else {
                    createAdminRequest()
                }
            }
        }
    }

    private fun createAdminRequest() {

        val createAdminRequest = CreateAdminRequest()
        val role = Role()
        val company = Company()
        val agency = Agency()


        createAdminRequest.firstName = edt_first_name.text.toString()
        createAdminRequest.middleName = edt_middle_name.text.toString()
        createAdminRequest.lastName = edt_last_name.text.toString()
        createAdminRequest.email = edt_email.text.toString()
        createAdminRequest.phone = edt_phone.text.toString()
        createAdminRequest.secondaryPhone = edt_secondary_phone.text.toString()
        createAdminRequest.gender = tvGender.text.toString()
        createAdminRequest.username = edt_user_name.text.toString()
        createAdminRequest.password = edt_password.text.toString()

        agency?.updatedAt = System.currentTimeMillis()
        agency?.createdAt = System.currentTimeMillis()
        agency?.addressOne = edt_address_one.text.toString()
        agency?.addressTwo = edt_address_two.text.toString()
        agency?.city = edt_address_city.text.toString()
        agency?.state = tvState.text.toString()
        agency?.zipcode = edt_address_zip.text.toString()
        agency?.status = selectedStaus.toLong()
        agency?.id = selectedAgencyId.toLong()

        createAdminRequest.deviceAddress = getDeviceId()
        createAdminRequest.fcmToken = getDeviceId()
        createAdminRequest.rememberToken = getDeviceId()
        createAdminRequest.menuList = menuList
        createAdminRequest.listGroupIds = groupList

        company.id=selectedCompanyId.toLong()

        role.level = selectedAdminTypeId.toLong()
        role.levelName = tvAdminType.text.toString()
        role.id = roleID.toLong()
        role.createdAt = System.currentTimeMillis()
        role.updatedAt = System.currentTimeMillis()
        createAdminRequest.role = role
        createAdminRequest.company = company
        createAdminRequest.agency = agency

        agencyViewModel?.addAgencyAdmin(createAdminRequest)
    }

    override fun dropDownItemClick(position: Int) {
        when {
            type.equals(COMPANY) -> {
                tvCompanyAdd.setText(allCompanyModel.data?.get(position)?.name)
                selectedCompanyId = allCompanyModel.data?.get(position)?.id.toString()
                mPopupWindow.dismiss()

            }
            type.equals(AGENCY) -> {
                tvAgencyAdd.setText(agencyDropDown.data?.get(position)?.name)
                selectedAgencyId = agencyDropDown.data?.get(position)?.id.toString()
                mPopupWindow.dismiss()
            }
            type.equals(GENDER) -> {
                tvGender.setText(getGenderArrayList().get(position))
                selectedGender = getGenderArrayList()?.get(position)
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

                selectedAdminTypeId = getRole?.data?.get(position)?.level.toString()
                roleID = getRole?.data?.get(position)?.id.toString()
                tvAdminType.text = getRole?.data?.get(position)?.levelName
                mPopupWindow.dismiss()
            }
        }
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


    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()
    }

    private fun unregisterObserver() {
        agencyViewModel!!.GetAgencyDropDown().removeObservers(this)
        agencyViewModel!!.getCompanyDropDown().removeObservers(this)
        agencyViewModel!!.getAllGroups().removeObservers(this)
        agencyViewModel!!.getEmployeeDropDownResponse().removeObservers(this)
    }
}