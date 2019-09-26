package com.expert.operrwork.view.userAccess.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_company_policy.*


/**
 * Created by Akshay.
 */
class CompanyPolicyFragment : BaseFragment() {

    var mSideMenu = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_company_policy, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (mSideMenu) {
            (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.tb_edit_signature)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        img_clear_sign.setOnClickListener {
            signature_view.clearCanvas()
        }

        btn_company_register.setOnClickListener {
            if (signature_view.isBitmapEmpty) {
                Toast.makeText(context!!, "please draw your signature", Toast.LENGTH_SHORT).show();
            } else {
                /*if (mSideMenu) {
                    fragmentManager?.popBackStackImmediate()
                }*/
                fragmentManager?.popBackStack(0,0)
                fragmentManager?.popBackStack()
            }
        }

    }
}