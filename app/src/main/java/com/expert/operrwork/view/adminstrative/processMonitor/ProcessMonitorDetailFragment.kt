package com.expert.operrwork.view.adminstrative.processMonitor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.databinding.FragmentProcessDetailBinding
import kotlinx.android.synthetic.main.fragment_process_monitor.*


class ProcessMonitorDetailFragment(id: Long?) : BaseFragment(){



    private var processMonitorViewModel: ProcessMonitorViewModel? = null
    var mProcessMonitorId=""
    lateinit var binding:FragmentProcessDetailBinding

    init {
        this.mProcessMonitorId=id.toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_process_detail,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processMonitorViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!)).get(ProcessMonitorViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvPath.text = HtmlCompat.fromHtml(getString(R.string.tb_home) +" > " +
                getString(R.string.side_menu_Adminstrative) +" > " +
                getString(R.string.adminstrative_process_monitor)+" > "+
                "<font color=black>" +  getString(R.string.detail)
                + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY)

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObserver()
        processMonitorViewModel?.getProcessMonitorById(mProcessMonitorId)

    }


    private fun initObserver() {


        processMonitorViewModel!!.getUserProcessMonitor().observe(this, Observer {
            if (it != null && it?.status.equals("SUCCESS")) {
              binding.model=it?.data
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()

    }

    private fun unregisterObserver() {
        processMonitorViewModel!!.getUserProcessMonitor().removeObservers(this)
    }
}
