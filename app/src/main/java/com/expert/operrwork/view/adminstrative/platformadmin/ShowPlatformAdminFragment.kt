package com.expert.operrwork.view.adminstrative.platformadmin

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
import com.expert.operrwork.data.model.response.allplatformadmin.AllPlateFormAdmin
import com.expert.operrwork.data.model.response.allplatformadmin.Content
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.util.helper.AppUtils
import kotlinx.android.synthetic.main.fragment_show_platform_admin.*


/**
 * Created by Akshay.
 */
class ShowPlatformAdminFragment : BaseFragment(), PlatformAdminAdapter.ItemClick {

    private lateinit var mAdapter: PlatformAdminAdapter
    private lateinit var getAllPlateFormAdmin: AllPlateFormAdmin
    private var platformAdminViewModel: PlatformAdminViewModel? = null
    val itemClick: PlatformAdminAdapter.ItemClick

    init {
        itemClick = this
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_platform_admin, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        platformAdminViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(PlatformAdminViewModel::class.java)

        platformAdminViewModel?.getAllPlateFormAdmin()


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObserver()
        listener()

        tvPath.text = HtmlCompat.fromHtml(getString(R.string.tb_home) +" > " +
                getString(R.string.side_menu_Adminstrative) +" > " +
                getString(R.string.adminstrative_platform_admin) +" > " +
                "<font color=black>" + getString(R.string.show)
                + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY)


    }

    private fun listener() {
        fab.setOnClickListener(View.OnClickListener {
            AppUtils.viewPager.setCurrentItem(1,true)
        })
    }


    private fun initObserver() {
        platformAdminViewModel!!.getAllPlateFormAdminList().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
                getAllPlateFormAdmin = it
                setAdapter(getAllPlateFormAdmin?.data?.content)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
    }

    fun setAdapter(
        data: List<Content>?
    ) {

        rv_plateform_admin.layoutManager = LinearLayoutManager(activity)
        mAdapter = PlatformAdminAdapter(data, platformAdminViewModel!!, itemClick)
        rv_plateform_admin?.adapter = mAdapter

    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putString("id", getAllPlateFormAdmin.data?.content?.get(position)?.id.toString())

        val editPlatformFragment = EditPlatformAdminFragment()
        editPlatformFragment.arguments = bundle

        addReplaceFragment(R.id.fl_adminstrative,editPlatformFragment,false,true,null)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()

    }

    private fun unregisterObserver() {
        platformAdminViewModel!!.getAllPlateFormAdminList().removeObservers(this)
    }

}