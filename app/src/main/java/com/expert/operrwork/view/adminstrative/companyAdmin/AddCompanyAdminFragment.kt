package com.expert.operrwork.view.adminstrative.companyAdmin

import android.Manifest
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.data.local.PreferenceManager
import com.expert.operrwork.data.model.createAdmin.CreateAdminRequest
import com.expert.operrwork.data.model.createAdmin.Role
import com.expert.operrwork.data.model.response.bringEmployee.EmployeeDropDownResponse
import com.expert.operrwork.data.model.response.getallcompany.GetAllCompanyDropDownResponse
import com.expert.operrwork.data.model.role.GetRoleResponse
import com.expert.operrwork.data.viewModelBaseContext.BaseContext
import com.expert.operrwork.util.helper.MarshMellowHelper
import com.expert.operrwork.util.helper.PathUtil
import kotlinx.android.synthetic.main.fragment_add_company_admin.*
import kotlinx.android.synthetic.main.fragment_add_company_admin.btn_save
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_address_city
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_address_one
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_address_two
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_address_zip
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_confirm_password
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_email
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_first_name
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_last_name
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_password
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_phone
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_secondary_phone
import kotlinx.android.synthetic.main.fragment_add_company_admin.edt_user_name
import kotlinx.android.synthetic.main.fragment_add_company_admin.imageViewUpload
import kotlinx.android.synthetic.main.fragment_add_company_admin.llAdminType
import kotlinx.android.synthetic.main.fragment_add_company_admin.llBringEmployee
import kotlinx.android.synthetic.main.fragment_add_company_admin.switchStatus
import kotlinx.android.synthetic.main.fragment_add_company_admin.textStatus
import kotlinx.android.synthetic.main.fragment_add_company_admin.tvAdminType
import kotlinx.android.synthetic.main.fragment_add_company_admin.tvBringEmployee
import kotlinx.android.synthetic.main.fragment_add_company_admin.tvGender
import kotlinx.android.synthetic.main.fragment_add_company_admin.tvPath
import java.io.IOException
import java.net.URISyntaxException


/**
 * Created by Akshay.
 */
class AddCompanyAdminFragment : BaseFragment(), View.OnClickListener {

    private var roleID = ""
    private var selectedAdminTypeId = ""
    private var arrayListStatus: ArrayList<String> = ArrayList()
    private var arrayListGender: ArrayList<String> = ArrayList()
    private var companyViewModel: CompanyViewModel? = null
    var mPreferenceManager: PreferenceManager? = null
    private var selectedCompanyId = ""
    private var selectedEmpId = ""
    private var selectedStaus = ""
    private lateinit var companyDropDownModel: GetAllCompanyDropDownResponse
    private lateinit var employeeDropDownResponse: EmployeeDropDownResponse
    private lateinit var type: String
    private val COMPANY = "company"
    private val EMPLOYEE = "emp"
    private val GENDER = "gender"
    private val ADMIN = "admin"

    private var marshMellowHelper: MarshMellowHelper? = null
    private val PERMISSIONS_REQUEST_CODE = 10003
    lateinit var selectFromGallery: Uri
    private var imagePath: String? = null
    private var mCameraImageUri: Uri? = null
    private val SELECT_FILE = 1
    private val REQUEST_CAMERA = 0
    var getRole = GetRoleResponse()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_company_admin, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
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
        companyViewModel =
            ViewModelProviders.of(this, BaseContext(mBaseActivity!!))
                .get(CompanyViewModel::class.java)

        tvPath.text = HtmlCompat.fromHtml(
            getString(R.string.tb_home) + " > " +
                    getString(R.string.side_menu_Adminstrative) + " > " +
                    getString(R.string.adminstrative_company_admin) + " > " +
                    "<font color=black>" + getString(R.string.add)
                    + "</font>",HtmlCompat.FROM_HTML_MODE_LEGACY
        )

    }

    private fun initObserver() {

        companyViewModel!!.getRoleResponse().observe(this, Observer {
            if (it?.status.equals("SUCCESS")) {
                getRole = it
                var listOfAdmin = mutableListOf<String>()
                for (item in it?.data!!) {
                    listOfAdmin.add(item.levelName!!)
                }
                intilizeAdapter(context!!, listOfAdmin, tvAdminType)

            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        companyViewModel!!.addUpdateCompanyResponse().observe(this, Observer {
            if (it?.status.equals("SUCCESS")) {
                showError(it?.message!!)
            } else {
                if (it?.message != null) {
                    showError(it?.message!!)
                } else {
                    showError(getString(R.string.str_something_wrong))
                }

            }
        })

        companyViewModel!!.getCompanyDropDown().observe(this, Observer {
            if (it != null && it.status.equals("SUCCESS")) {
                companyDropDownModel = it
                var listOfCompanyName = mutableListOf<String>()
                for (item in it.data!!) {
                    listOfCompanyName.add(item.name!!)
                }
                intilizeAdapter(context!!, listOfCompanyName, tvCompany)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

        companyViewModel!!.getEmployeeDropDownResponse().observe(this, Observer {
            if (it != null && it.status.equals("SUCCESS")) {
                employeeDropDownResponse = it
                var listOfCompanyName = mutableListOf<String>()
                for (item in it.data!!) {
                    listOfCompanyName.add(item?.username!!)
                }
                intilizeAdapter(context!!, listOfCompanyName, tvCompany)
            } else {
                showError(getString(R.string.str_something_wrong))
            }
        })

    }


    private fun listener() {
        btn_save.setOnClickListener(this)
        linearLayoutCompany.setOnClickListener(this)
        llBringEmployee.setOnClickListener(this)
        llAdminType.setOnClickListener(this)
        tvGender.setOnClickListener(this)
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
        when {
            type.equals(COMPANY) -> {
                tvCompany.text = companyDropDownModel.data?.get(position)?.name
                selectedCompanyId = companyDropDownModel.data?.get(position)?.id.toString()
                mPopupWindow.dismiss()
            }
            type.equals(EMPLOYEE) -> {
                tvBringEmployee.text = employeeDropDownResponse.data?.get(position)?.username
                selectedEmpId = employeeDropDownResponse.data?.get(position)?.id.toString()

                edt_first_name.setText(employeeDropDownResponse.data?.get(position)?.firstName)
                edt_last_name.setText(employeeDropDownResponse.data?.get(position)?.lastName)
                if (employeeDropDownResponse.data?.get(position)?.status?.equals("1")!!) {
                    textStatus.setText("ACTIVE")
                    switchStatus.isChecked = true
                } else {
                    textStatus.setText("IN_ACTIVE")
                    switchStatus.isChecked = false
                }
                tvGender.setText(employeeDropDownResponse.data?.get(position)?.gender)
                edt_email.setText(employeeDropDownResponse.data?.get(position)?.email)
                edt_phone.setText(employeeDropDownResponse.data?.get(position)?.phone)
                edt_secondary_phone.setText(employeeDropDownResponse.data?.get(position)?.phone)
                mPopupWindow.dismiss()
            }
            type.equals(GENDER) -> {
                tvGender.text = getGenderArrayList().get(position)
                mPopupWindow.dismiss()
            }
            type.equals(ADMIN) -> {
                selectedAdminTypeId = getRole.data?.get(position)?.level.toString()
                roleID = getRole.data?.get(position)?.id.toString()
                tvAdminType.text = getRole.data?.get(position)?.levelName
                mPopupWindow.dismiss()
            }
        }
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.btn_save -> {

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
            R.id.linearLayoutCompany -> {
                type = COMPANY
                companyViewModel?.getComapnyListDropDown()
            }

            R.id.llAdminType -> {
                type = ADMIN
                companyViewModel?.getCompanyAdminRole()
            }

            R.id.llBringEmployee -> {
                if (selectedCompanyId.isEmpty()) {
                    showError(getString(R.string.please_select_company))
                } else {
                    type = EMPLOYEE
                    companyViewModel?.getEmployeeDropDown(selectedCompanyId)
                }
            }
            R.id.tvGender -> {
                type = GENDER
                intilizeAdapter(context!!, getGenderArrayList(), tvGender)
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

        val createAdminRequest = CreateAdminRequest()
        val role = Role()
        createAdminRequest.firstName = edt_first_name.text.toString()
        createAdminRequest.lastName = edt_last_name.text.toString()
        createAdminRequest.email = edt_email.text.toString()
        createAdminRequest.phone = edt_phone.text.toString()
        createAdminRequest.secondaryPhone = edt_secondary_phone.text.toString()
        createAdminRequest.gender = tvGender.text.toString()
        createAdminRequest.username = edt_user_name.text.toString()
        createAdminRequest.password = edt_password.text.toString()

        createAdminRequest.company?.updatedAt = System.currentTimeMillis()
        createAdminRequest.company?.createdAt = System.currentTimeMillis()
        createAdminRequest.company?.addressOne = edt_address_one.text.toString()
        createAdminRequest.company?.addressTwo = edt_address_two.text.toString()
        createAdminRequest.company?.city = edt_address_city.text.toString()
        createAdminRequest.company?.state = edt_state.text.toString()
        createAdminRequest.company?.zipcode = edt_address_zip.text.toString()
        createAdminRequest.company?.status = selectedStaus.toLong()

        createAdminRequest.deviceAddress = getDeviceId()
        createAdminRequest.fcmToken = getDeviceId()
        createAdminRequest.rememberToken = getDeviceId()

        role.level = selectedAdminTypeId.toLong()
        role.levelName = tvAdminType.text.toString()
        role.id = roleID.toLong()
        role.createdAt = System.currentTimeMillis()
        role.updatedAt = System.currentTimeMillis()
        createAdminRequest.role = role

        companyViewModel?.addCompanyAdmin(createAdminRequest)
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
                    imagePath = PathUtil.getPath(context!!, selectFromGallery)
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
        companyViewModel!!.getCompanyDropDown().removeObservers(this)
        companyViewModel!!.getEmployeeDropDownResponse().removeObservers(this)
    }
}