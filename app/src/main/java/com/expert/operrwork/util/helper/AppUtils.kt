package com.expert.operrwork.util.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.expert.operrwork.view.adminstrative.agencyAdmin.AdminPagerAdapter


class AppUtils {

    companion object AppUtils {
        var arrayListFragment: ArrayList<Fragment> = ArrayList()
        var arrayListTitle: ArrayList<String> = ArrayList()
        lateinit var fragmentAdapter: AdminPagerAdapter
        lateinit var viewPager: ViewPager
        lateinit var tabLayout: TabLayout


        fun setupViewPager(
            viewPager: ViewPager,
            tabLayout: TabLayout, childFragmentManager: FragmentManager,
            list: ArrayList<Fragment>, listOfTitles: ArrayList<String>
        ) {
            this.viewPager = viewPager
            this.tabLayout = tabLayout

            fragmentAdapter = AdminPagerAdapter(
                list,
                listOfTitles, childFragmentManager
            )
            viewPager.adapter = fragmentAdapter
            tabLayout.setupWithViewPager(viewPager)

        }
/*
        fun notifyViewPager(list: ArrayList<Fragment>, listOfTitles: ArrayList<String>) {
            fragmentAdapter.customNotify(list, listOfTitles)
        }
*/

        fun removeTab(
            position: Int) {
            tabLayout.removeTabAt(position);
            arrayListFragment.removeAt(position)
            arrayListTitle.removeAt(position)

            fragmentAdapter.customNotify(arrayListFragment, arrayListTitle)
        }

        fun addTab(title: String,fragment: Fragment) {
            tabLayout.addTab(tabLayout.newTab().setText(title))
            arrayListFragment.add(fragment)
            arrayListTitle.add(title)

            fragmentAdapter.customNotify(arrayListFragment, arrayListTitle)


        }

    }

}