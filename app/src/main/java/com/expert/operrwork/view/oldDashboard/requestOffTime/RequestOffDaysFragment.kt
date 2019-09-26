package com.expert.operrwork.view.oldDashboard.requestOffTime

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.bottom_sheet_review.*
import kotlinx.android.synthetic.main.fragment_request_off_days.*


/**
 * Created by Akshay.
 */
class RequestOffDaysFragment : BaseFragment() {

    private var mBottomSheetBehaviour: BottomSheetBehavior<*>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_request_off_days, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("InlinedApi")
    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.tb_request_off_days)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        bottomSide()
        btn_edit_request_off_submit.setOnClickListener {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun bottomSide() {
        mBottomSheetBehaviour = BottomSheetBehavior.from(ReviewScrollView)
        mBottomSheetBehaviour!!.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(p0: View, p1: Int) {
            }

            override fun onSlide(p0: View, p1: Float) {
            }
        })

        btn_review_done.setOnClickListener {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        btn_review_cancel.setOnClickListener {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity!!.menuInflater.inflate(R.menu.request_off_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_request_off) {
            openRequestMenuFragment()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun openRequestMenuFragment() {
        addReplaceFragment(R.id.fl_container, RequestMenuFragment(), false, true, null)
    }
}