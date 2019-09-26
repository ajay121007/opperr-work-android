package com.expert.operrwork.view.adminstrative.platformadmin

import android.Manifest
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.model.createPlatformAdmin.request.CreatePlatformAdminRequest
import com.expert.operrwork.data.model.createPlatformAdmin.request.Role
import com.expert.operrwork.data.model.menu.MenuItem
import com.expert.operrwork.data.model.role.GetRoleResponse
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.util.helper.MarshMellowHelper
import com.expert.operrwork.util.helper.PathUtil
import com.expert.operrwork.view.adminstrative.MenuAdapter
import com.expert.operrwork.view.adminstrative.MenuDialogAdapter
import kotlinx.android.synthetic.main.dialog_menu_list.*
import kotlinx.android.synthetic.main.fragment_add_plateform_admin.*
import java.io.IOException
import java.net.URISyntaxException


/**
 * Created by Akshay.
 */
class AddPlatformAdminFragment : BaseFragment(), View.OnClickListener {

    private var arrayListStatus: ArrayList<String> = ArrayList()
    private var arrayListGender: ArrayList<String> = ArrayList()
    private var platformAdminViewModel: PlatformAdminViewModel? = null
    var menuList: ArrayList<Long> = ArrayList()
    private var selectedStaus = ""
    private lateinit var type: String
    private val GENDER = "gender"
    private val ADMIN = "admin"
    private var selectedAdminID = ""
    private var roleID = ""
    var getRole = GetRoleResponse()

    private var marshMellowHelper: MarshMellowHelper? = null
    private val PERMISSIONS_REQUEST_CODE = 10003
    lateinit var selectFromGallery: Uri
    private var imagePath: String? = null
    private var mCameraImageUri: Uri? = null
    private val SELECT_FILE = 1
    private val REQUEST_CAMERA = 0
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menuItem: MenuItem
    private lateinit var mMenuDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_plateform_admin, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        getMenuItems()
        setMenuAdapter()
        listener()
        initObserver()
    }


    private fun init() {
        marshMellowHelper = MarshMellowHelper(
            this, arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), PERMISSIONS_REQUEST_CODE
        )
        platformAdminViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!))
                .get(PlatformAdminViewModel::class.java)

        tvPath.text = HtmlCompat.fromHtml(
            getString(R.string.tb_home) + " > " +
                    getString(R.string.side_menu_Adminstrative) + " > " +
                    getString(R.string.adminstrative_platform_admin) + " > " +
                    "<font color=black>" + getString(R.string.add)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )

    }

    private fun initObserver() {
        platformAdminViewModel!!.createPlatformAdminResponse().observe(this, Observer {
            if (it?.status.equals("SUCCESS")) {
                showError(it?.message!!)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        platformAdminViewModel!!.getRoleResponse().observe(this, Observer {
            if (it?.status.equals("SUCCESS")) {
                getRole=it
                var listOfAdmin = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfAdmin.add(item.levelName!!)
                }
                intilizeAdapter(context!!, listOfAdmin, tvAdminType)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })
    }


    private fun listener() {
        btn_save.setOnClickListener(this)
        llGender.setOnClickListener(this)
        llAdminType.setOnClickListener(this)
        tvAddAnother.setOnClickListener(this)
        imageViewUpload.setOnClickListener(this)

        switchStatus.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                selectedStaus = "1"
                textStatus.text = "ACTIVE"
            } else {
                selectedStaus = "0"
                textStatus.text = "INACTIVE"

            }
        }

    }

    override fun dropDownItemClick(position: Int) {
        if (type.equals(GENDER)) {
            tvGender.text = getGenderArrayList().get(position)
            mPopupWindow.dismiss()
        }
        else if (type.equals(ADMIN)) {
            selectedAdminID = getRole?.data?.get(position)?.level.toString()
            roleID = getRole?.data?.get(position)?.id.toString()
            tvAdminType.text = getRole?.data?.get(position)?.levelName
            mPopupWindow.dismiss()
        }
    }

    private fun getStatusArrayList(): ArrayList<String> {
        arrayListStatus.clear()
        arrayListStatus.add("ACTIVE")
        arrayListStatus.add("INACTIVE")
        return arrayListStatus
    }



    private fun setMenuAdapter() {
        rv_menus.setLayoutManager(LinearLayoutManager(activity))
        menuAdapter = MenuAdapter(menuItem.menu_list!!)
        rv_menus.adapter = menuAdapter
    }

    private fun getMenuItems() {
        val menuString = resources.openRawResource(R.raw.menu_list)
            .bufferedReader().use { it.readText() }

        menuItem = Gson().fromJson(menuString, MenuItem::class.java)
    }

    private fun showMenuDialog() {

        mMenuDialog = Dialog(activity)
        mMenuDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mMenuDialog.setCancelable(false)
        mMenuDialog.setContentView(R.layout.dialog_menu_list)

        val menuDialogAdapter = MenuDialogAdapter(
            menuItem.menu_list!!
        )

        mMenuDialog.recyclerView.setLayoutManager(LinearLayoutManager(activity))

        mMenuDialog.recyclerView.setAdapter(menuDialogAdapter)

        mMenuDialog.btn_ok.setOnClickListener(View.OnClickListener {
            for (i in 0 until menuItem.menu_list!!.size - 1) {
                if (menuItem.menu_list!!.get(i).checked) {
                    if (!menuList.contains(menuItem.menu_list!!.get(i).id?.toLong())) {
                        menuList.add(menuItem.menu_list!!.get(i).id?.toLong()!!)
                    }
                    Log.d("checkedName", menuItem.menu_list!!.get(i).name)
                }
            }
            menuAdapter.notifyDataSetChanged()
            mMenuDialog.dismiss()
        })

        mMenuDialog.show()
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.btn_save -> {
                mBaseActivity?.hideKeyboard(activity!!)
                if (edt_first_name.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_first_name))
                } else if (edt_last_name.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_last_name))
                } else if (edt_email.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_email))
                } else if (!mBaseActivity!!.isValidEmail(edt_email.text.toString())) {
                    showError(getString(R.string.error_valid_email))
                } else if (tvGender.text.toString().isEmpty()) {
                    showError(getString(R.string.select_gender))
                } else if (edt_address_one.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_address))
                } else if (tvAdminType.text.toString().isEmpty()) {
                    showError(getString(R.string.select_admin_type))
                } else if (edt_user_name.text.toString().isEmpty()) {
                    showError(getString(R.string.empty_user_name))
                } else if (!mBaseActivity!!.isValidPassword(edt_password.text.toString())) {
                    showError(getString(R.string.password_must))
                } else if (!edt_password.text.toString().equals(edt_confirm_password.text.toString())) {
                    showError(getString(R.string.password_did_not_match))
                } else {
                    createAdminRequest()
                }
            }

            R.id.tvAddAnother -> {
                showMenuDialog()
            }
            R.id.llGender -> {
                type = GENDER
                intilizeAdapter(context!!, getGenderArrayList(), tvGender)
            }
            R.id.llAdminType -> {
                type = ADMIN
                platformAdminViewModel?.getPlatformAdminRole()
            }
            R.id.imageViewUpload -> {
                marshMellowHelper!!.request(object : MarshMellowHelper.PermissionCallback {
                    override fun onPermissionGranted() {
                        dialogChooseImagesOptions() //Your Function
                    }

                    override fun onPermissionDenied() {

                    }

                    override fun onPermissionDeniedBySystem() {

                    }
                })
            }

        }
    }

    private fun createAdminRequest() {

        val createAdminRequest = CreatePlatformAdminRequest()
        val role = Role()


        createAdminRequest.firstName= edt_first_name.text.toString()
        createAdminRequest.middleName= edt_middle_name.text.toString()
        createAdminRequest.lastName= edt_last_name.text.toString()
        createAdminRequest.email= edt_email.text.toString()
        createAdminRequest.phone= edt_phone.text.toString()
        createAdminRequest.secondaryPhone= edt_secondary_phone.text.toString()
        createAdminRequest.gender= tvGender.text.toString()
        createAdminRequest.username= edt_user_name.text.toString()
        createAdminRequest.did=edt_did.text.toString()
        createAdminRequest.createdAt=System.currentTimeMillis()
        createAdminRequest.updatedAt=System.currentTimeMillis()
        createAdminRequest.deviceAddress=getDeviceId()
        createAdminRequest.fcmToken=getDeviceId()
        createAdminRequest.rememberToken=getDeviceId()
        createAdminRequest.isChangeStatusNotification=false
        createAdminRequest.menuList=menuList


        role.level= selectedAdminID.toLong()
        role.levelName= tvAdminType.text.toString()
        role.id= roleID.toLong()
        role.createdAt=System.currentTimeMillis()
        role.updatedAt=System.currentTimeMillis()
        createAdminRequest.role=role


        platformAdminViewModel?.createPlatformAdmin(createAdminRequest)

    }


    private fun dialogChooseImagesOptions() {
        val dialogs = Dialog(context!!, R.style.AppThemeUser)
        dialogs.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialogs.window!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.transparentBlack)))
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(false)
        dialogs.setContentView(R.layout.dialog_image_chooser)
        val gallery = dialogs.findViewById(R.id.img_gallery) as AppCompatImageView
        val camera = dialogs.findViewById(R.id.img_camera) as AppCompatImageView
        val cancel = dialogs.findViewById(R.id.img_close) as AppCompatImageView

        gallery.setOnClickListener {
            galleryIntent()
            dialogs.dismiss()
        }

        camera.setOnClickListener {
            cameraIntent()
            dialogs.dismiss()
        }
        cancel.setOnClickListener {
            dialogs.dismiss()
        }
        dialogs.show()
    }

    private fun galleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE)
    }

    private fun cameraIntent() {
        val values = ContentValues()
        values.put(
            "title",
            System.currentTimeMillis().toString() + resources.getString(R.string.app_name) + "_image"
        )
        mCameraImageUri =
            activity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val intentCamera = Intent("android.media.action.IMAGE_CAPTURE")
        intentCamera.putExtra("output", mCameraImageUri)
        startActivityForResult(intentCamera, REQUEST_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_FILE) run {
            var bm: Bitmap? = null
            if (data != null) {
                Glide.with(this).load(data.data).into(imageViewUpload)
                selectFromGallery = data.data!!
                try {
                    imagePath = PathUtil.getPath(context!!, selectFromGallery!!)
                } catch (e: URISyntaxException) {
                    e.printStackTrace()
                }

                try {
                    bm = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, data.data)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        } else if (requestCode == REQUEST_CAMERA) run {
            if (mCameraImageUri != null) {
                Glide.with(this).load(mCameraImageUri).into(imageViewUpload)
                try {
                    imagePath = PathUtil.getPath(context!!, mCameraImageUri!!)
                } catch (e: URISyntaxException) {
                    e.printStackTrace()
                }
            }
        }
    }

    //Getting Request Permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (marshMellowHelper != null) {
            marshMellowHelper!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObserver()

    }

    private fun unregisterObserver() {
        platformAdminViewModel?.createPlatformAdminResponse()?.removeObservers(this)
    }


}