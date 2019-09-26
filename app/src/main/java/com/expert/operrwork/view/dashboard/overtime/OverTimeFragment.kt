package com.expert.operrwork.view.dashboard.overtime

import android.os.Bundle
import android.view.*
import androidx.core.text.HtmlCompat
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_admin_browse.*


/**
 * Created by Akshay.
 */
class OverTimeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overtime, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        tvPath.text = HtmlCompat.fromHtml(
            getString(R.string.str_login)  + " > " +
                    getString(R.string.str_dashboard) + " > " +
                    "<font color=black>" + getString(R.string.dashboard_overtime)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }


}