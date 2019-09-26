package com.expert.operrwork.view.adminstrative.agencyAdmin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.util.helper.AppUtils
import com.expert.operrwork.util.helper.AppUtils.AppUtils.arrayListFragment
import com.expert.operrwork.util.helper.AppUtils.AppUtils.arrayListTitle
import com.expert.operrwork.util.helper.AppUtils.AppUtils.viewPager
import kotlinx.android.synthetic.main.fragment_agency_admin.*


/**
 * Created by Akshay.
 */
class AgencyAdminFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_agency_admin, container, false)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title =
            resources.getString(R.string.adminstrative_agency_admin)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        tabListener()
    }

    private fun tabListener() {
        viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                Log.d("removedPositions", position.toString());
                if (arrayListFragment.size>3&&position<3){
                    init()
                    AppUtils.viewPager.setCurrentItem(
                        position,
                        true)

                }

            }
        })

    }


    private fun init() {
        AppUtils.setupViewPager(
            viewpager_main, tabs_main, childFragmentManager
            , getAgencyAdminFragmentList(), getAgencyAdminTitleList()
        )
    }
    fun getAgencyAdminFragmentList(): ArrayList<Fragment> {
        arrayListFragment.clear()
        arrayListFragment.add(BrowseFragment())
        arrayListFragment.add(AddAgencyAdminFragment())
        arrayListFragment.add(SettingsFragment())
        return arrayListFragment
    }

    fun getAgencyAdminTitleList(): ArrayList<String> {
        arrayListTitle.clear()
        arrayListTitle.add(getString(com.expert.operrwork.R.string.browse))
        arrayListTitle.add(getString(com.expert.operrwork.R.string.str_add_admin))
        arrayListTitle.add(getString(com.expert.operrwork.R.string.str_settings))
        return arrayListTitle
    }

}