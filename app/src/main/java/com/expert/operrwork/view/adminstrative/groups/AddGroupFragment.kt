package com.expert.operrwork.view.adminstrative.groups

import android.app.Dialog
import android.os.Bundle
import android.util.Log
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
import com.expert.operrwork.data.model.adminDropDown.Datum
import com.expert.operrwork.data.model.adminDropDown.GetAdminDropDown
import com.expert.operrwork.data.model.group.addUpdateGroup.request.AddUpdateGroupRequest
import com.expert.operrwork.data.model.group.addUpdateGroup.request.AdminGroup
import com.expert.operrwork.data.model.menu.MenuItem
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.view.adminstrative.MenuAdapter
import com.expert.operrwork.view.adminstrative.MenuDialogAdapter
import kotlinx.android.synthetic.main.dialog_menu_list.*
import kotlinx.android.synthetic.main.fragment_add_company_admin.switchStatus
import kotlinx.android.synthetic.main.fragment_add_company_admin.textStatus
import kotlinx.android.synthetic.main.fragment_add_group.*
import kotlinx.android.synthetic.main.fragment_add_group.rv_menus
import kotlinx.android.synthetic.main.fragment_add_group.tvAddAnother
import kotlinx.android.synthetic.main.fragment_add_group.tvPath


/**
 * Created by Akshay.
 */
class AddGroupFragment : BaseFragment(), View.OnClickListener {

    private lateinit var selectedStaus: String
    private var groupViewModel: GroupViewModel? = null
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menuItem: MenuItem
    private lateinit var mMenuDialog: Dialog
    var getAdminDropDown = GetAdminDropDown()
    private lateinit var mGroupDialog: Dialog
    private var adminList: ArrayList<Datum> = ArrayList()
    var menuList: ArrayList<Long> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_group, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        getMenuItems()
        setMenuAdapter()
        listener()
        initObserver()
    }

    private fun init() {

        groupViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!))
                .get(GroupViewModel::class.java)

        tvPath.text = HtmlCompat.fromHtml(
            getString(R.string.tb_home) + " > " +
                    getString(R.string.side_menu_Adminstrative) + " > " +
                    getString(R.string.adminstrative_groups) + " > " +
                    "<font color=black>" + getString(R.string.add)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )

    }

    private fun initObserver() {
        groupViewModel!!.getAdminDropDownResponse().observe(this, Observer {
            if (it?.status.equals("SUCCESS")) {
                getAdminDropDown = it
                if (it?.data?.size!! > 0)
                    showAdminDialog()
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        groupViewModel!!.addUpdateGroupResponse().observe(this, Observer {
            if (it?.status.equals("SUCCESS")) {
                showError(it?.message!!)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

    }


    private fun listener() {
        btn_add.setOnClickListener(this)
        tvAddAnother.setOnClickListener(this)
        tvAdmins.setOnClickListener(this)

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

    override fun dropDownItemClick(position: Int) {


    }


    private fun setMenuAdapter() {
        rv_menus.setLayoutManager(LinearLayoutManager(activity))
        menuAdapter = MenuAdapter(menuItem.menu_list!!)
        rv_menus.adapter = menuAdapter
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


    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.btn_add -> {

                if (edt_group_name.text.toString().isEmpty()){
                    showError(getString(R.string.empty_group_name))
                }else{

                    val request= AddUpdateGroupRequest ()
                    val adminGroupList :MutableList<AdminGroup> = mutableListOf()
                    request.menuList=menuList
                    request.name=edt_group_name.text.toString()
                    request.status=textStatus.text.toString()

                    for (it in adminList){
                        val adminGroup= AdminGroup()
                          adminGroup.name=it.name
                          adminGroup.id=it.id.toString()
                          adminGroupList.add(adminGroup)

                    }
                    request.adminGroups=adminGroupList
                    groupViewModel?.addGroup(request)
                }

            }
            R.id.tvAddAnother -> {
                showMenuDialog()
            }

            R.id.tvAdmins -> {
                if (getAdminDropDown.data==null||getAdminDropDown.data?.size == 0) {
                    groupViewModel?.getAdminDropDown()
                } else {
                    showAdminDialog()
                }

            }
        }
    }

    private fun showAdminDialog() {
        mGroupDialog = Dialog(activity)
        mGroupDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mGroupDialog.setCancelable(false)
        mGroupDialog.setContentView(R.layout.dialog_menu_list)
        val mAdminDialogAdapter =
            AdminDialogAdapter(
                getAdminDropDown.data
            )
        mGroupDialog.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        mGroupDialog.recyclerView.setAdapter(mAdminDialogAdapter)
        mGroupDialog.btn_ok.setOnClickListener(View.OnClickListener {
            adminList.clear()
            for (i in 0 until getAdminDropDown.data!!.size - 1) {
                if (getAdminDropDown.data!!.get(i).checked!!) {
                    adminList.add(getAdminDropDown.data!!.get(i))
                }
            }
            if (adminList.size > 1)
                tvAdmins.setText(adminList.size.toString() + " Item Selected")
            else if (adminList.size == 1) {
                tvAdmins.append(adminList.get(0).name + "")
            }
            mGroupDialog.dismiss()
        })
        mGroupDialog.show()
    }

}