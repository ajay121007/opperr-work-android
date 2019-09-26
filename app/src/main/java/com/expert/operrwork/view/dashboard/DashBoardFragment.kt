package com.expert.operrwork.view.dashboard

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.view.dashboard.home.HomeFragment
import com.expert.operrwork.view.dashboard.overtime.OverTimeFragment
import com.expert.operrwork.view.dashboard.todo.ToDoFragment
import kotlinx.android.synthetic.main.fragment_dashboard_.*


/**
 * Created by Akshay.
 */
class DashBoardFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard_, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

        override fun onStart() {
            super.onStart()
            (activity as AppCompatActivity).supportActionBar!!.title =
                resources.getString(R.string.str_dashboard)
        }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = DashboardPagerAdapter(childFragmentManager)
        adapter.addFragment(HomeFragment(), getString(R.string.tb_home))
        adapter.addFragment(OverTimeFragment(), getString(R.string.dashboard_overtime))
        adapter.addFragment(ToDoFragment(), getString(R.string.dashboard_todo))
        viewpagerDashboard.adapter = adapter
        tabLayoutDashboard.setupWithViewPager(viewpagerDashboard)
    }

}