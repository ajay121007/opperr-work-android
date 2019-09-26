package com.expert.operrwork.view.oldDashboard.news

import android.os.Bundle
import android.view.*
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.eventBus.NotificationEventBus
import org.greenrobot.eventbus.EventBus

/**
 * Created by Akshay.
 */
class NewsUpdateExtendFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_updates_extended, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
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