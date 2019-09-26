package com.expert.operrwork.view.operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_adminstrative_option.*
import kotlinx.android.synthetic.main.fragment_dashboard.rv_menus


/**
 * Created by Akshay.
 */
class OperationOptionFragment : BaseFragment() {

    var mOperationAdapter: OperationAdapter? = null
    var mData = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_operation_option, container, false)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.str_operation)
;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        tv_path.text = HtmlCompat.fromHtml(getString(R.string.tb_home) +" > "
                +"<font color=black>" + getString(R.string.str_operation)
                + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun init() {
        mData.clear()
        resources.getStringArray(R.array.operation).toCollection(mData)
        setAdminRecyclerView()
    }

    //after getting data it update the Product in recyclerView
    private fun setAdminRecyclerView() {
        val layoutManager = LinearLayoutManager(context!!)
        rv_menus.layoutManager = layoutManager
        mOperationAdapter =
            OperationAdapter(context!!, mData, object : OperationAdapter.ProductClickListener {
                override fun itemClick(position: Int) {
                    when {
                       /* mData[position].contentEquals(resources.getString(R.string.adminstrative_agency_admin)) -> addReplaceFragment(
                            R.id.fl_operation,
                            AgencyAdminFragment(),
                            false,
                            true,
                            null
                        )*/
                    }
                }
            })
        rv_menus.adapter = mOperationAdapter
        mOperationAdapter!!.notifyDataSetChanged()
    }


}