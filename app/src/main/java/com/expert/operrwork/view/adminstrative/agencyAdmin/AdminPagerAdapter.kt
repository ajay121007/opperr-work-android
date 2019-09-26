package com.expert.operrwork.view.adminstrative.agencyAdmin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter


class  AdminPagerAdapter(

    arrayListFragment: ArrayList<Fragment>,
    arrayListTitle: ArrayList<String>,
    childFragmentManager: FragmentManager

) : FragmentPagerAdapter(childFragmentManager) {

    private var baseId: Long = 0
        var fragmentArrayList: ArrayList<Fragment>;
    var fragmentTitleArrayList: ArrayList<String>;

    init {
        this.fragmentTitleArrayList = arrayListTitle;
        this.fragmentArrayList = arrayListFragment;
    }

    override fun getItem(position: Int): Fragment {
        return fragmentArrayList.get(position)
    }

    override fun getCount(): Int {
        return fragmentTitleArrayList.size
    }

    override fun getPageTitle(position: Int): String {
        return fragmentTitleArrayList.get(position)

    }

    fun customNotify(
        list: ArrayList<Fragment>,
        listOfTitles: ArrayList<String>
    ) {
        fragmentArrayList = list
        fragmentTitleArrayList = listOfTitles
        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE
    }

    override fun getItemId(position: Int): Long {
        // give an ID different from position when position has been changed
        return baseId + position
    }

    fun removeFragment(fragment: Fragment, position: Int) {
        fragmentArrayList.removeAt(position)
        fragmentTitleArrayList.removeAt(position)
    }

    fun addFragment(fragment: Fragment, title: String, position: Int) {
        fragmentArrayList.add(position, fragment)
        fragmentTitleArrayList.add(position, title)
    }


}