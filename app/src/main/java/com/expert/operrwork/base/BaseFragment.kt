package com.expert.operrwork.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings.Secure
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.PopupWindow
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.expert.operrwork.view.adapter.ItemAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


abstract class BaseFragment : Fragment(), BaseView, ItemAdapter.ItemClickHandler {


    private var arrayListGender: ArrayList<String> = ArrayList()
    var mBaseActivity: BaseActivity? = null
    var mLastClickTime: Long = 0
    lateinit var mPopupWindow: PopupWindow
    private var recylcerView: RecyclerView? = null



    override fun showError(error: String) {
        try {
            if (mBaseActivity != null) mBaseActivity!!.showError(error)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showConnectionError() {
        try {
            if (mBaseActivity != null) mBaseActivity!!.showConnectionError()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showLoader() {
        try {
            if (mBaseActivity != null) mBaseActivity!!.showLoader()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun hideLoader() {
        try {
            if (mBaseActivity != null) mBaseActivity!!.hideLoader()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /* To avoid multiple clicks on screen at a same time */
    fun isClickEnable(): Boolean {
        val timeBetweenClick = 600 //in ns
        if (SystemClock.elapsedRealtime() - mLastClickTime < timeBetweenClick) return false
        else {
            mLastClickTime = SystemClock.elapsedRealtime()
            return true
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mBaseActivity = context as BaseActivity?
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    fun addReplaceFragment(@IdRes container: Int, fragment: Fragment, addFragment: Boolean, addToBackStack: Boolean, animationHolder: AnimationHolder?) {
        if (activity == null) return
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        if (animationHolder != null) {
            //            transaction.setCustomAnimations(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left, R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
            transaction.setCustomAnimations(animationHolder.enterAnim, animationHolder.exitAnim, animationHolder.popEnterAim, animationHolder.popExitAim)
        }
        if (addFragment) {
            transaction.add(container, fragment, fragment.javaClass.simpleName)
        } else {
            transaction.replace(container, fragment, fragment.javaClass.simpleName)
        }
        if (addToBackStack) {
            transaction.addToBackStack(fragment.tag)
        }
        hideKeyboard(activity!!)
        transaction.commit()
    }


    fun addReplaceChildFragment(@IdRes container: Int, fragment: Fragment, addFragment: Boolean, addToBackStack: Boolean, animationHolder: AnimationHolder?) {
        val transaction = childFragmentManager.beginTransaction()
        if (animationHolder != null) {
            //            transaction.setCustomAnimations(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left, R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
            transaction.setCustomAnimations(animationHolder.enterAnim, animationHolder.exitAnim, animationHolder.popEnterAim, animationHolder.popExitAim)
        }
        if (addFragment) {
            transaction.add(container, fragment, fragment.javaClass.simpleName)
        } else {
            transaction.replace(container, fragment, fragment.javaClass.simpleName)
        }
        if (addToBackStack) {
            transaction.addToBackStack(fragment.tag)
        }
        hideKeyboard(activity!!)
        transaction.commit()
    }

    fun hideKeyboard(activity: Activity) {
        var imm: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) view = View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun currentDateWithWeekName(patternFormat: String): String? {
        return SimpleDateFormat(patternFormat, Locale.ENGLISH).format(Calendar.getInstance().time)
    }

    @SuppressLint("SimpleDateFormat")
    fun currentTime(mCommonTimeFormat: String): String? {
        return SimpleDateFormat(mCommonTimeFormat).format(Calendar.getInstance().time)
    }

    fun intilizeAdapter(context: Context, list: MutableList<String>, view: View){
        val adapter = ItemAdapter(this, list)
        showPopWindow(context,view, adapter)
    }

    fun showPopWindow(context: Context,view: View, adapter: ItemAdapter) {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val customView = LayoutInflater.from(context).inflate(
            com.expert.operrwork.R.layout.view_pop_window,
            null)
        recylcerView = customView.findViewById(com.expert.operrwork.R.id.recyclerView)
        recylcerView?.setLayoutManager(LinearLayoutManager(context))
        recylcerView?.setAdapter(adapter)
        mPopupWindow = PopupWindow(customView, view.width,
            WindowManager.LayoutParams.WRAP_CONTENT)
        mPopupWindow.setOutsideTouchable(true)
        mPopupWindow.setBackgroundDrawable(BitmapDrawable())
        mPopupWindow.showAsDropDown(view, 0, 10)
    }

    override fun dropDownItemClick(position: Int) {

    }

    fun getDeviceId():String{
        val android_id = Secure.getString(
            context!!.contentResolver,
            Secure.ANDROID_ID
        )
        return android_id
    }

    fun getGenderArrayList(): ArrayList<String> {
        arrayListGender.clear()
        arrayListGender.add("MALE")
        arrayListGender.add("FEMALE")
        return arrayListGender
    }


}
