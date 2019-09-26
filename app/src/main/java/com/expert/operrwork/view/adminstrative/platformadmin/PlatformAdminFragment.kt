package com.expert.operrwork.view.adminstrative.platformadmin

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
import kotlinx.android.synthetic.main.fragment_agency_admin.*


/**
 * Created by Akshay.
 */
class PlatformAdminFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_platform_admin, container, false)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title =
            resources.getString(R.string.adminstrative_platform_admin)
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
        AppUtils.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                Log.d("removedPositions", position.toString());
                if (AppUtils.arrayListFragment.size>2&&position<2){
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
        AppUtils.arrayListFragment.clear()
        AppUtils.arrayListFragment.add(ShowPlatformAdminFragment())
        AppUtils.arrayListFragment.add(AddPlatformAdminFragment())
        return AppUtils.arrayListFragment
    }

    fun getAgencyAdminTitleList(): ArrayList<String> {
        AppUtils.arrayListTitle.clear()
        AppUtils.arrayListTitle.add(getString(R.string.str_show_platform_admins))
        AppUtils.arrayListTitle.add(getString(R.string.str_add_platform_admin))
        return AppUtils.arrayListTitle
    }

}