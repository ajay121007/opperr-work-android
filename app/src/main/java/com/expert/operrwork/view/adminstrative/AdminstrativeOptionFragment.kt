package com.expert.operrwork.view.adminstrative

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.local.PreferenceManager
import com.expert.operrwork.view.adminstrative.agencyAdmin.AgencyAdminFragment
import com.expert.operrwork.view.adminstrative.companyAdmin.CompanyAdminFragment
import com.expert.operrwork.view.adminstrative.groups.GroupFragment
import com.expert.operrwork.view.adminstrative.platformadmin.PlatformAdminFragment
import com.expert.operrwork.view.adminstrative.processMonitor.ProcessMonitorFragment
import kotlinx.android.synthetic.main.fragment_adminstrative_option.*
import kotlinx.android.synthetic.main.fragment_dashboard.rv_menus


/**
 * Created by Akshay.
 */
class AdminstrativeOptionFragment : BaseFragment() {


    var mSideMenu = false
    var mAdminstrativeAdapter: AdminstrativeAdapter? = null
    var mData = ArrayList<String>()
    var mPreferenceManager: PreferenceManager? = null
    var firstTime = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_adminstrative_option, container, false)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.str_admininstrative)
;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreference()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        tv_path.text = HtmlCompat.fromHtml(getString(R.string.tb_home) +" > "
                +"<font color=black>" + getString(R.string.side_menu_Adminstrative)
                + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun init() {
        mData.clear()
        resources.getStringArray(R.array.adminstrative).toCollection(mData)
        setAdminRecyclerView()
    }

    //after getting data it update the Product in recyclerView
    private fun setAdminRecyclerView() {
        val layoutManager = LinearLayoutManager(context!!)
        rv_menus.layoutManager = layoutManager
        mAdminstrativeAdapter =
            AdminstrativeAdapter(context!!, mData, object : AdminstrativeAdapter.ProductClickListener {
                override fun itemClick(position: Int) {
                    when {
                        mData[position].contentEquals(resources.getString(R.string.adminstrative_agency_admin)) -> addReplaceFragment(
                            R.id.fl_adminstrative,
                            AgencyAdminFragment(),
                            false,
                            true,
                            null
                        )
                        mData[position].contentEquals(resources.getString(R.string.adminstrative_company_admin)) -> addReplaceFragment(
                            R.id.fl_adminstrative,
                            CompanyAdminFragment(),
                            false,
                            true,
                            null
                        )
                        mData[position].contentEquals(resources.getString(R.string.adminstrative_platform_admin)) -> addReplaceFragment(
                            R.id.fl_adminstrative,
                            PlatformAdminFragment(),
                            false,
                            true,
                            null
                        )
                        mData[position].contentEquals(resources.getString(R.string.adminstrative_groups)) -> addReplaceFragment(
                            R.id.fl_adminstrative,
                            GroupFragment(),
                            false,
                            true,
                            null
                        )
                        mData[position].contentEquals(resources.getString(R.string.adminstrative_process_monitor)) ->
                            addReplaceFragment(
                            R.id.fl_adminstrative,
                            ProcessMonitorFragment(),
                            false,
                            true,
                            null
                        )
                    }
                }
            })
        rv_menus.adapter = mAdminstrativeAdapter
        mAdminstrativeAdapter!!.notifyDataSetChanged()
    }


    fun initSharedPreference() {
        val prefs = activity!!.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)
        mPreferenceManager = PreferenceManager(prefs)
    }


}