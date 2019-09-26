package com.expert.operrwork.view.oldDashboard.news

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.demo.News
import com.expert.operrwork.data.model.eventBus.NotificationEventBus
import com.expert.operrwork.util.Constant
import kotlinx.android.synthetic.main.fragment_updates.*
import org.greenrobot.eventbus.EventBus


/**
 * Created by Akshay.
 */
class NewsFragment : BaseFragment() {
    var mNewsUpdateAdapter: NewsUpdateAdapter? = null
    var mData = ArrayList<News>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_updates, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.tb_updates)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        prepareList()
    }

    private fun prepareList() {
        mData.clear()
        for ((i, e) in (Constant.topic).withIndex()) {
            mData.add(News(Constant.DemoHistoryDays[i], "John Doe", e))
        }
        setDashboardRecyclerView()
    }


    //after getting data it update the Product in recyclerView
    private fun setDashboardRecyclerView() {
        val layoutManager = LinearLayoutManager(context!!)
        rv_update_news.layoutManager = layoutManager
        mNewsUpdateAdapter = NewsUpdateAdapter(context!!, mData, object : NewsUpdateAdapter.ProductClickListener {
            override fun itemClick(position: Int) {
                addReplaceFragment(R.id.fl_container, NewsUpdateExtendFragment(), false, true, null)
            }
        })
        rv_update_news.adapter = mNewsUpdateAdapter
        mNewsUpdateAdapter!!.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity!!.menuInflater.inflate(R.menu.update_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_update_setting) {
            openNotification()
            true
        } else super.onOptionsItemSelected(item)
    }

    //open Notification via EventBus
    private fun openNotification() {
        EventBus.getDefault().post(NotificationEventBus(true))
    }
}