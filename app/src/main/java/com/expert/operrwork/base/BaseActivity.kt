package com.expert.operrwork.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.expert.operrwork.R
import com.expert.operrwork.data.local.PreferenceManager
import java.util.regex.Matcher
import java.util.regex.Pattern


abstract class BaseActivity : AppCompatActivity(), BaseView {
    var mBaseProgressDialog: BaseProgressDialogFragment? = null

    var mLastClickTime: Long = 0
    var mPreferenceManager: PreferenceManager? = null


    /**
     * show the progress
     */
    private fun showProgressDialog() {
        if (mBaseProgressDialog == null) {
            mBaseProgressDialog = BaseProgressDialogFragment().getInstance()
        }
        mBaseProgressDialog!!.show(supportFragmentManager, "ProgressDialog")
    }

    fun hideProgressDialog() {
        mBaseProgressDialog?.dismiss()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)
        mPreferenceManager = PreferenceManager(prefs)
    }


    override fun onPause() {
        hideProgressDialog()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun showError(error: String) {
        Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_SHORT).show()
    }

    // ------------------------------------------------------------
    // Activity Transition Support functions
    // ------------------------------------------------------------

    fun startActivity(intent: Intent, animate: Boolean) {
        super.startActivity(intent)
        if (animate) overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left)
    }


    fun startActivityForResult(intent: Intent, requestCode: Int, animate: Boolean) {
        super.startActivityForResult(intent, requestCode)

        if (animate) overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left)
    }

    fun finish(animate: Boolean) {
        super.finish()
        if (animate) overridePendingTransition(R.anim.animation_slide_to_left, R.anim.animation_slide_from_right)
    }


    fun addReplaceFragment(@IdRes container: Int, fragment: Fragment, addFragment: Boolean, addToBackStack: Boolean, animationHolder: AnimationHolder?) {

        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

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
        hideKeyboard(this)
        transaction.commit()
    }


    /**
     * it show the network connection are available or not
     */
    override fun showConnectionError() {
        showError(resources.getString(R.string.error_internet_connection_common))
    }

    override fun showLoader() {
        showProgressDialog()
    }

    override fun hideLoader() {
        hideProgressDialog()
    }

    /**
     * checking password match patterns
     *
     */
    fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty() && password.isNotBlank() && password.length > 6 /*&& checkPassswordFormat(password)*/
    }

    /*
   (?=.*\d)      #   must contains one digit from 0-9
   (?=.*[a-z])   #   must contains one lowercase characters
   (?=.*[A-Z])   #   must contains one uppercase characters
   (?=.*[@#$%])   #   must contains one special symbols in the list "@#$%"
   . #   match anything with previous condition checking
   {6,12}        #   length at least 4 characters and maximum of 20
   */
    fun checkPassswordFormat(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,12})";
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhone(phone: String): Boolean {
        return phone.isNotEmpty() && phone.isNotBlank() && Patterns.PHONE.matcher(phone).matches()
    }


    fun showApiError(responseString: String) {
        when {
            responseString.contains("201") -> showError(resources.getString(R.string.status_201))
            responseString.contains("400") -> showError(resources.getString(R.string.status_400))
            responseString.contains("404") -> showError(resources.getString(R.string.status_404))
            responseString.contains("409") -> showError(resources.getString(R.string.status_409))
            responseString.contains("422") -> showError(resources.getString(R.string.status_422))
            responseString.contains("423") -> showError(resources.getString(R.string.status_423))
            responseString.contains("500") -> showError(resources.getString(R.string.status_500))
            else -> showError(responseString)
        }
    }


    fun hideKeyboard(activity: Activity) {
        var imm: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) view = View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}