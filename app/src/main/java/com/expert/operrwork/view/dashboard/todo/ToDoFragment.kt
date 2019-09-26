package com.expert.operrwork.view.dashboard.todo

import android.os.Bundle
import android.view.*
import androidx.core.text.HtmlCompat
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_admin_browse.*


/**
 * Created by Akshay.
 */
class ToDoFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

        override fun onStart() {
            super.onStart()
        }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()

        tvPath.text = HtmlCompat.fromHtml(
            getString(R.string.str_login) + " > "  +
                    getString(R.string.str_dashboard) + " > " +
                    "<font color=black>" + getString(R.string.todo_list)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }


    private fun init() {
    }


}