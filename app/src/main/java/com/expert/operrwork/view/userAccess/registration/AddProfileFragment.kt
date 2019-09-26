package com.expert.operrwork.view.userAccess.registration

import android.Manifest
import android.annotation.SuppressLint
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
import com.bumptech.glide.Glide
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import com.expert.operrwork.util.helper.MarshMellowHelper
import com.expert.operrwork.util.helper.PathUtil
import kotlinx.android.synthetic.main.fragment_add_profile.*
import java.io.IOException
import java.net.URISyntaxException


/**
 * Created by Akshay.
 */
class AddProfileFragment : BaseFragment() {
    private var marshMellowHelper: MarshMellowHelper? = null
    private val PERMISSIONS_REQUEST_CODE = 10003
    lateinit var selectFromGallery: Uri
    private var imagePath: String? = null
    private var mCameraImageUri: Uri? = null
    private val SELECT_FILE = 1
    private val REQUEST_CAMERA = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_profile, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    @SuppressLint("InlinedApi")
    private fun init() {
        marshMellowHelper = MarshMellowHelper(this, arrayOf(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSIONS_REQUEST_CODE)

        btn_add_profile_continue.setOnClickListener {
            addReplaceFragment(R.id.fl_container,
                CreatePinFragment(), false, true, null)
        }

        txt_skip_this.setOnClickListener {
            addReplaceFragment(R.id.fl_container,
                CreatePinFragment(), false, true, null)
        }

        img_upload.setOnClickListener {
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

    private fun dialogChooseImagesOptions() {
        val dialogs = Dialog(context!!,R.style.AppThemeUser)
        dialogs.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
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
        values.put("title", System.currentTimeMillis().toString() + resources.getString(R.string.app_name) + "_image")
        mCameraImageUri = activity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val intentCamera = Intent("android.media.action.IMAGE_CAPTURE")
        intentCamera.putExtra("output", mCameraImageUri)
        startActivityForResult(intentCamera, REQUEST_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_FILE) run {
            var bm: Bitmap? = null
            if (data != null) {
                Glide.with(this).load(data.data).into(img_upload)
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
                Glide.with(this).load(mCameraImageUri).into(img_upload)
                try {
                    imagePath = PathUtil.getPath(context!!, mCameraImageUri!!)
                } catch (e: URISyntaxException) {
                    e.printStackTrace()
                }
            }
        }
    }

    //Getting Request Permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (marshMellowHelper != null) {
            marshMellowHelper!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}