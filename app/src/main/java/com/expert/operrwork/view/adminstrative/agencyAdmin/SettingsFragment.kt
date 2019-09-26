package com.expert.operrwork.view.adminstrative.agencyAdmin

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TimePicker
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.local.PreferenceManager
import com.expert.operrwork.data.model.agency.DaysBean
import com.expert.operrwork.data.model.agency.IPRestrictionBean
import com.expert.operrwork.data.model.agency.IPRestrictionTimeBean
import com.expert.operrwork.data.model.agency.settings.request.SettingsRequest
import com.expert.operrwork.data.model.companyAdminById.GetCompanyAdminById
import com.expert.operrwork.data.model.loginNameById.LoginName
import com.expert.operrwork.data.model.response.getagencybyid.GetAgencyByCompId
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import kotlinx.android.synthetic.main.dialog_days.*
import kotlinx.android.synthetic.main.dialog_days.tvCancel
import kotlinx.android.synthetic.main.dialog_days.tvNext
import kotlinx.android.synthetic.main.dialog_ip_restiction.*
import kotlinx.android.synthetic.main.dialog_menu_list.btn_ok
import kotlinx.android.synthetic.main.dialog_time_picker.*
import kotlinx.android.synthetic.main.fragment_admin_settings.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Akshay.
 */
class SettingsFragment : BaseFragment(), View.OnClickListener {

    private lateinit var timeRestrictionBean: IPRestrictionTimeBean.IPListBean
    private var curentDay = 0
    private var daysArray: ArrayList<Int> = ArrayList()

    private lateinit var ipRestrictionAdapter: IPRestrictionAdapter
    private lateinit var ipRestrictionTimeAdapter: IPRestrictionTimeAdapter
    private var arrayListDays: ArrayList<String> = ArrayList()
    private var arrayListDaysBean: ArrayList<DaysBean.DaysListBean> = ArrayList()
    private lateinit var mIPRestrictionDialog: Dialog
    private lateinit var mIPRestrictionByTimeDialog: Dialog
    private lateinit var mDaysDialog: Dialog
    private lateinit var mTimeDialog: Dialog
    private var agencyViewModel: AgencyViewModel? = null
    var agencyDropDown = GetAgencyByCompId()
    var ipRestrictionBean = IPRestrictionBean()
    var mDaysBean = DaysBean()
    var ipRestrictionBeanList: ArrayList<IPRestrictionBean.IPListBean> = ArrayList()
    var ipRestrictionTimeBeanList: ArrayList<IPRestrictionTimeBean.IPListBean> = ArrayList()
    var ipRestrictionTimeBean = IPRestrictionTimeBean()
    var loginDropDown = LoginName()
    var adminDetails = GetCompanyAdminById()
    var companyDropDown = GetAllCompanyDropDownResponse()
    lateinit var type: String
    private val PASSWORD_EXPIRY = "password_expiry"
    private val COMPANY = "company"
    private val LOGIN_NAME = "loginName"
    private val AGENCY = "agency"
    private var selectedCompanyId = ""
    private var selectedAgencyId = ""
    private var selectedAgencyPosition:Int?=null
    private var selectedLoginId = ""
    private var passwordExpiry = 0
    private var roleId = 0

    var mPreferenceManager: PreferenceManager? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin_settings, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreference()
        agencyViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(AgencyViewModel::class.java)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
        listner()
        initObserver()
        tvPath.text = HtmlCompat.fromHtml(
            getString(R.string.tb_home) + " > " +
                    getString(R.string.side_menu_Adminstrative) + " > " +
                    getString(R.string.adminstrative_agency_admin) + " > " +
                    "<font color=black>" + getString(R.string.action_settings)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )

    }

    private fun listner() {
        btn_save.setOnClickListener(this)
        ll_company.setOnClickListener(this)
        ll_agency.setOnClickListener(this)
        tvLoginName.setOnClickListener(this)
        tv_password_expiry.setOnClickListener(this)
        tvAddRestrictIP.setOnClickListener(this)
        tvAddRestrictByTime.setOnClickListener(this)
    }


    private fun setAdapter() {
        addDays()
        rv_ip_restriction_time.setLayoutManager(LinearLayoutManager(activity))
        rv_ip_restriction.setLayoutManager(LinearLayoutManager(activity))

        ipRestrictionTimeAdapter = IPRestrictionTimeAdapter(
            ipRestrictionTimeBean.ip_list!!
        )
        rv_ip_restriction_time.adapter = ipRestrictionTimeAdapter


        ipRestrictionAdapter = IPRestrictionAdapter(
            ipRestrictionBean?.ip_list!!
        )
        rv_ip_restriction.adapter = ipRestrictionAdapter

    }

    private fun addDays() {
        arrayListDays = arrayListOf(*resources.getStringArray(R.array.days))

        for (i in 0..arrayListDays.size - 1) {
            val bean = DaysBean.DaysListBean()
            bean.days = arrayListDays.get(i)
            arrayListDaysBean.add(bean)
        }
        mDaysBean.days_list = arrayListDaysBean

    }


    private fun initObserver() {
        agencyViewModel!!.getUpdateSettings().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                showError(it.message!!)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
        agencyViewModel!!.getCompanyDropDown().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                companyDropDown = it
                var listOfCompanyName = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfCompanyName.add(item.name!!)
                }
                intilizeAdapter(context!!, listOfCompanyName, tvCompany)
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

        agencyViewModel!!.getLoginNameDropDown().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                loginDropDown = it
                var listOfAdminName = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfAdminName.add(item.name!!)
                }
                intilizeAdapter(context!!, listOfAdminName, tvLoginName)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
        agencyViewModel!!.getAdminDetails().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                adminDetails = it
                edt_first_name.setText(adminDetails.data?.firstName)
                edt_middle_name.setText(adminDetails.data?.middleName.toString())
                edt_last_name.setText(adminDetails.data?.lastName)
                tv_email.setText(adminDetails.data?.email)
                tv_sms.setText(adminDetails.data?.phone)
                roleId= adminDetails.data?.role?.id?.toInt()!!
                if (adminDetails.data?.isEmailNotification!!)
                    switchEmail.setChecked(true)
                if (adminDetails.data?.isSMSNotification!!)
                    switchSMS.setChecked(true)

                //selectedCompanyId = adminDetails.data!!.companyId.toString()

            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_agency -> {
                type = AGENCY
                if (selectedCompanyId.isEmpty())
                    showError(resources.getString(R.string.please_select_company))
                else
                    agencyViewModel?.getAgencyByCompanyIdDropDown(selectedCompanyId)

            }
            R.id.ll_company -> {
                type = COMPANY
                agencyViewModel?.getComapnyListDropDown()
            }
            R.id.tv_password_expiry -> {
                type = PASSWORD_EXPIRY
                intilizeAdapter(context!!, getPasswordExpiryList(), tv_password_expiry)
            }
            R.id.tvAddRestrictIP -> {
                showIPRestrictionDaialog()
            }
            R.id.tvAddRestrictByTime -> {
                showIPRestrictionByTimeDaialog()

            }

            R.id.btn_save -> {
                if (tvCompany.text.toString().isEmpty())
                    showError(getString(R.string.select_company_first))
                else if (tvAgency.text.toString().isEmpty()) {
                    showError(getString(R.string.select_agency))
                } else if (tvLoginName.text.toString().isEmpty()) {
                    showError(getString(R.string.select_login_name))
                } else if (tv_password_expiry.text.toString().isEmpty()) {
                    showError(getString(R.string.select_password_expiry))
                } else if (edt_first_name.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_first_name))
                } else if (edt_last_name.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_last_name))
                } else {
                    var updateSettings = SettingsRequest()
                    var ipRestrictionTimeBean = SettingsRequest.ListIpHourlyRestrictedBean()
                    var ipRestrictionBean = SettingsRequest.ListIpRestrictedBean()
                    var hourly: ArrayList<SettingsRequest.ListIpHourlyRestrictedBean> = ArrayList()
                    var ipRestrictList: ArrayList<SettingsRequest.ListIpRestrictedBean> = ArrayList()

                    updateSettings.companyId = selectedCompanyId
                    updateSettings.createdAt = adminDetails.data?.createdAt!!
                    updateSettings.createdByUsr = adminDetails.data?.createdByUsr
                    updateSettings.deviceAddress = adminDetails.data?.deviceAddress
                    updateSettings.did = adminDetails.data?.did
                    updateSettings.email = adminDetails.data!!.email
                    updateSettings.username = adminDetails.data!!.username
                    updateSettings.id = adminDetails?.data?.id?.toInt()
                    updateSettings.status = adminDetails?.data?.status!!
                    updateSettings.firstName = adminDetails.data!!.firstName
                    updateSettings.gender = adminDetails.data!!.gender
                    updateSettings.ip = adminDetails.data!!.ip
                    updateSettings.username = adminDetails.data!!.username
                    updateSettings.status = adminDetails.data!!.status!!
                    updateSettings.secondaryPhone = adminDetails.data!!.secondaryPhone
                    updateSettings.updatedAt = adminDetails.data?.updatedAt!!
                    updateSettings.middleName =adminDetails.data!!.middleName
                    updateSettings.lastName =adminDetails.data!!.lastName
                    updateSettings.phone = adminDetails.data?.phone!!
                    updateSettings.renewPasswordInterval = passwordExpiry
                    updateSettings.isEmailNotification = switchEmail.isChecked
                    updateSettings.isSMSNotification = switchSMS.isChecked
                    updateSettings.isIpHourlyRestricted = checkBoxTimeRestrictIP.isChecked
                    updateSettings.isIpRestricted = checkBoxRestrictIP.isChecked
                    updateSettings.isChangeStatusNotification = checkBoxStatus.isChecked
                    updateSettings.isOverrideOTLimit = adminDetails.data?.isOverrideOTLimit!!
                    updateSettings.listSelectedEmailPositionId = adminDetails.data!!.listSelectedEmailPositionId
                    updateSettings.listSelectedSMSPositionId = adminDetails.data!!.listSelectedSMSPositionId

                    ipRestrictionTimeBean.dayOfWeek = daysArray.count()
                    ipRestrictionTimeBean.ipAddress = timeRestrictionBean.ip
                    ipRestrictionTimeBean.timeEnd = getCurrentDay()
                    ipRestrictionTimeBean.timeStart = getCurrentDay()
                    ipRestrictionTimeBean.isChecked = timeRestrictionBean.checked

                    hourly.add(ipRestrictionTimeBean)
                    updateSettings.listIpHourlyRestricted = hourly

                    ipRestrictionBean.ipAddress = timeRestrictionBean.ip

                    ipRestrictList.add(ipRestrictionBean)
                    updateSettings.listIpRestricted = ipRestrictList


                    agencyViewModel?.updateSettings(updateSettings)

                }

            }

            R.id.tvLoginName -> {
                type = LOGIN_NAME
                if (selectedAgencyId.isEmpty())
                    showError(resources.getString(R.string.please_select_agency))
                else
                    agencyViewModel?.loginNameDropDown(selectedAgencyId, "3")
            }
        }
    }

    private fun getPasswordExpiryList(): MutableList<String> {
        var arrayList: MutableList<String> = ArrayList()

        arrayList.add("1 Month")
        arrayList.add("2 Months")
        arrayList.add("3 Months")
        arrayList.add("6 Months")
        arrayList.add("9 Months")
        arrayList.add("12 Months")

        return arrayList
    }

    fun initSharedPreference() {
        val prefs = activity!!.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)
        mPreferenceManager = PreferenceManager(prefs)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObservers()
    }

    override fun dropDownItemClick(position: Int) {
        if (type.equals(COMPANY)) {
            tvCompany.setText(companyDropDown.data?.get(position)?.name)
            selectedCompanyId = companyDropDown.data?.get(position)?.id.toString()
            mPopupWindow.dismiss()

        } else if (type.equals(AGENCY)) {
            tvAgency.setText(agencyDropDown.data?.get(position)?.name)
            selectedAgencyId = agencyDropDown.data?.get(position)?.id.toString()
            selectedAgencyPosition =position
            mPopupWindow.dismiss()
        } else if (type.equals(LOGIN_NAME)) {
            tvLoginName.setText(loginDropDown.data?.get(position)?.name)
            selectedLoginId = loginDropDown.data?.get(position)?.id.toString()
            agencyViewModel?.getAdminById(selectedLoginId)
            mPopupWindow.dismiss()
        } else if (type.equals(PASSWORD_EXPIRY)) {
            tv_password_expiry.setText(getPasswordExpiryList().get(position))
            passwordExpiry = Integer.parseInt(getPasswordExpiryList().get(position).substring(0, 1))
            mPopupWindow.dismiss()
        }
    }


    private fun unregisterObservers() {
        agencyViewModel!!.GetAgencyDropDown().removeObservers(this)
        agencyViewModel!!.getAgencyDropDownById().removeObservers(this)
        agencyViewModel!!.getAdminDetails().removeObservers(this)
        agencyViewModel!!.getLoginNameDropDown().removeObservers(this)
    }

    private fun showIPRestrictionDaialog() {

        mIPRestrictionDialog = Dialog(activity)
        mIPRestrictionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mIPRestrictionDialog.setContentView(R.layout.dialog_ip_restiction)

        mIPRestrictionDialog.btn_ok.setOnClickListener {

            if (!mIPRestrictionDialog.edt_IP.text.toString().isEmpty()) {
                val bean = IPRestrictionBean.IPListBean()
                bean.ip = mIPRestrictionDialog.edt_IP.text.toString()
                bean.checked = mIPRestrictionDialog.switchStatus.isChecked
                ipRestrictionBeanList.add(bean)
                ipRestrictionBean.ip_list = ipRestrictionBeanList
                ipRestrictionAdapter.customNotify(ipRestrictionBean?.ip_list!!)
            }

            mIPRestrictionDialog.dismiss()
        }


        mIPRestrictionDialog.show()
    }

    private fun showIPRestrictionByTimeDaialog() {

        mIPRestrictionByTimeDialog = Dialog(activity)
        mIPRestrictionByTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mIPRestrictionByTimeDialog.setContentView(R.layout.dialog_ip_restiction)
        mIPRestrictionByTimeDialog.btn_ok.text = resources.getString(R.string.next)

        mIPRestrictionByTimeDialog.btn_ok.setOnClickListener {

            if (!mIPRestrictionByTimeDialog.edt_IP.text.toString().isEmpty()) {
                timeRestrictionBean = IPRestrictionTimeBean.IPListBean()
                timeRestrictionBean.ip = mIPRestrictionByTimeDialog.edt_IP.text.toString()
                timeRestrictionBean.checked = mIPRestrictionByTimeDialog.switchStatus.isChecked
                // ipRestrictionTimeBeanList.add(timeRestrictionBean)
                //   ipRestrictionTimeBean.ip_list = ipRestrictionTimeBeanList
                // ipRestrictionTimeAdapter.customNotify(ipRestrictionTimeBean?.ip_list!!)
            }

            mIPRestrictionByTimeDialog.dismiss()
            showDaysDaialog()
        }


        mIPRestrictionByTimeDialog.show()
    }

    private fun showDaysDaialog() {

        daysArray = ArrayList()
        curentDay = 0
        mDaysDialog = Dialog(activity)
        mDaysDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDaysDialog.setContentView(R.layout.dialog_days)
        mDaysDialog.rv_days.setLayoutManager(LinearLayoutManager(activity))
        var daysAdapter = DaysAdapter(mDaysBean.days_list)
        mDaysDialog.rv_days.adapter = daysAdapter

        mDaysDialog.tvCancel.setOnClickListener {
            mDaysDialog.dismiss()
        }

        mDaysDialog.tvNext.setOnClickListener {
            var days = ""
            for (i in 0..mDaysBean.days_list?.size!! - 1) {
                if (mDaysBean.days_list!!.get(i).checked) {
                    daysArray.add(i)
                    days = days + " " + mDaysBean.days_list!!.get(i).days?.substring(0, 2);
                }
            }
            if (daysArray.size<1){
             showError(resources.getString(R.string.select_days))
            }else {
                timeRestrictionBean.days = days
                showStartTimeDaialog(true)
                mDaysDialog.dismiss()

            }
        }

        mDaysDialog.show()
    }

    private fun showStartTimeDaialog(isStartTime: Boolean) {


        mTimeDialog = Dialog(activity)
        mTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mTimeDialog.setContentView(R.layout.dialog_time_picker)

        val timePicker = mTimeDialog.findViewById(R.id.timePicker) as TimePicker
        if (isStartTime)
            mTimeDialog.textViewTitle.text =
                resources.getString(R.string.pick_start_time) + " " + mDaysBean.days_list?.get(daysArray.get(curentDay))?.days
        else
            mTimeDialog.textViewTitle.text =
                resources.getString(R.string.pick_end_time) + " " + mDaysBean.days_list?.get(daysArray.get(curentDay - 1))?.days

        if (isStartTime)
            curentDay++
        mTimeDialog.tvCancel.setOnClickListener {
            mTimeDialog.dismiss()
        }

        mTimeDialog.tvNext.setOnClickListener {
            if (isStartTime)
                timeRestrictionBean.startTime =
                    timePicker.getCurrentHour().toString() + ":" + timePicker.getCurrentMinute()
            else
                timeRestrictionBean.endTime =
                    timePicker.getCurrentHour().toString() + ":" + timePicker.getCurrentMinute()

            ;
            if (!isStartTime && curentDay == daysArray.count()) {
                mTimeDialog.dismiss()
                ipRestrictionTimeBeanList.add(timeRestrictionBean)
                ipRestrictionTimeBean.ip_list = ipRestrictionTimeBeanList
                ipRestrictionTimeAdapter.customNotify(ipRestrictionTimeBean?.ip_list!!)
            } else {
                mTimeDialog.dismiss()
                if (isStartTime)
                    showStartTimeDaialog(false)
                else
                    showStartTimeDaialog(true)

            }

        }

        mTimeDialog.show()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDay(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val currentDate = sdf.format(Date())
        return  currentDate;
    }

}