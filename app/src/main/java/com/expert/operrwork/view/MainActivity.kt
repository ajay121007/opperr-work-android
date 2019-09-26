package com.expert.operrwork.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseActivity
import com.expert.operrwork.view.adapter.SideMenuAdapter
import com.expert.operrwork.view.oldDashboard.news.CompnayNewsFragment
import com.expert.operrwork.data.model.eventBus.NotificationEventBus
import com.expert.operrwork.view.userAccess.UserAccessActivity
import com.expert.operrwork.view.userAccess.registration.CreatePinFragment
import com.expert.operrwork.view.adminstrative.AdminstrativActivity
import com.expert.operrwork.view.dashboard.DashBoardFragment
import com.expert.operrwork.view.operation.OperationActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.bottom_sheet_notification.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe


class  MainActivity : BaseActivity() {

    private var doubleBackToExitPressedOnce: Boolean = false
    var mSideMenuAdapter: SideMenuAdapter? = null
    private var mArrayList = ArrayList<String>()
    private var mBottomSheetBehaviour: BottomSheetBehavior<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setSideMenuRecyclerView()

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //back button Action perform in toolbar if fragment are loaded
        toolbar.setNavigationOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                hideKeyboard(this)
                mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
                supportFragmentManager.popBackStack()
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }

        //back button show in toolbar if fragment are loaded
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                toggle.isDrawerIndicatorEnabled = false
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                supportActionBar!!.title = resources.getString(R.string.tb_hello_username)
                toggle.isDrawerIndicatorEnabled = true
            }
        }
        bottomSide()
        addReplaceFragment(R.id.fl_container, DashBoardFragment(), false, false, null)
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @SuppressLint("NewApi")
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount > 0) {
            hideKeyboard(this)
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
            supportFragmentManager.popBackStack()
        } else if (!doubleBackToExitPressedOnce) {
            doubleBackToExitPressedOnce = true
            showError(resources.getString(R.string.msg_press_back_to_exit))
            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        } else {
            super.onBackPressed()
            finishAffinity()
        }
    }

    //after getting data it update the Side menu in recyclerView
    private fun setSideMenuRecyclerView() {
        resources.getStringArray(R.array.side_menu).toCollection(mArrayList!!)
        val layoutManager = LinearLayoutManager(this@MainActivity)
        rv_side_menu.layoutManager = layoutManager
        mSideMenuAdapter = SideMenuAdapter(
            this@MainActivity,
            mArrayList,
            object : SideMenuAdapter.ProductClickListener {
                override fun itemClick(position: Int) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
                    hideKeyboard(this@MainActivity)
                    when {

                        mArrayList[position].contentEquals(resources.getString(R.string.str_admininstrative)) -> {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    AdminstrativActivity::class.java
                                )
                            )
                        }
                        mArrayList[position].contentEquals(resources.getString(R.string.str_operation)) -> {
                            startActivity(Intent(this@MainActivity, OperationActivity::class.java))
                        }
                    }
                }
            })
        rv_side_menu.adapter = mSideMenuAdapter
        mSideMenuAdapter!!.notifyDataSetChanged()
    }

    //open the reset pin code
    fun openCreatePin() {
        if (checkFragmentOpen(CreatePinFragment::class.java.simpleName)) {
            val mCreatePinFragment = CreatePinFragment()
            mCreatePinFragment.mSideMenu = true
            addReplaceFragment(R.id.fl_container, mCreatePinFragment, false, true, null)
        }
    }

    private fun checkFragmentOpen(simpleName: String): Boolean {
        var flag = false
        val mFragment = supportFragmentManager.findFragmentByTag(simpleName)
        if (mFragment == null) {
            flag = true
        } else {
            flag = false
            showError(resources.getString(R.string.msg_already_open))
        }
        return flag
    }

    @SuppressLint("SimpleDateFormat")
    private fun bottomSide() {
        mBottomSheetBehaviour = BottomSheetBehavior.from(NotificationScrollView)
        mBottomSheetBehaviour!!.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(p0: View, p1: Int) {
            }

            override fun onSlide(p0: View, p1: Float) {
            }
        })

        btn_turn_off_notification.setOnClickListener {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        btn_create_content.setOnClickListener {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
            addReplaceFragment(R.id.fl_container, CompnayNewsFragment(), true, true, null)
        }

        btn_notification_cancel.setOnClickListener {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    //Open Logout Dialog
    private fun dialogLogOut() {
        val dialogs = Dialog(this@MainActivity, R.style.AppThemeUser)
        dialogs.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialogs.window!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.transparentBlack)))
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(false)
        dialogs.setContentView(R.layout.dialog_logout)
        val btnYes = dialogs.findViewById(R.id.btn_logout_yes) as AppCompatButton
        val btnCancel = dialogs.findViewById(R.id.btn_logout_cancel) as AppCompatButton

        btnYes.setOnClickListener {
            dialogs.dismiss()
            openLoginScreen()
        }
        btnCancel.setOnClickListener {
            dialogs.dismiss()
        }

        dialogs.show()
    }

    //transfer to login screen when click on Log Out
    private fun openLoginScreen() {
        mPreferenceManager!!.clear()
        startActivity(Intent(this@MainActivity, UserAccessActivity::class.java))
        this@MainActivity.finish()
    }

    //open notification BottomSheet via Event Bus
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(notificationEventBus: NotificationEventBus) {
        if (notificationEventBus.notificationClick) {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            mBottomSheetBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}
