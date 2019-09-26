package com.expert.operrwork.util.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import com.expert.operrwork.R
import java.io.File
import java.io.IOException

/**
 * Created by Akshay.
 */
@SuppressLint("ValidFragment")
class ImageGettingHelper(private val imageGettingCallBack: ImageGettingCallBack, var activity: Activity) {

    private val ACTION_CAPTURE_FROM_CAMERA = 1001
    private val ACTION_SELECT_FROM_GALLERY = 1002
    private var mCameraImageUri: Uri? = null
    private var imagePath: String? = ""

    fun ProfileChoose(activity: Activity) {
        val dialog: Dialog
        dialog = Dialog(activity, R.style.AppTheme)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.setContentView(R.layout.dialog_image_chooser)
        val imgClose = dialog.findViewById<View>(R.id.img_close) as AppCompatImageButton
        val imgCamera = dialog.findViewById<View>(R.id.img_camera) as AppCompatImageView
        val imgGallery = dialog.findViewById<View>(R.id.img_gallery) as AppCompatImageView
        dialog.show()

        imgClose.setOnClickListener { dialog.dismiss() }

        imgGallery.setOnClickListener {
            selectImageFromGallery()
            dialog.dismiss()
            DebugLog().d("Gallery: Click")
        }

        imgCamera.setOnClickListener {
            captureImageFromCamera()
            dialog.dismiss()
            DebugLog().d("Camera: Click")
        }
    }

    fun captureImageFromCamera() {
        val values = ContentValues()
        val imageName =
            System.currentTimeMillis().toString() + activity.resources.getString(R.string.app_name) + "_image"
        values.put(MediaStore.Images.Media.TITLE, imageName)
        mCameraImageUri = activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, mCameraImageUri)
        activity.startActivityForResult(intentCamera, ACTION_CAPTURE_FROM_CAMERA)
    }

    fun selectImageFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), ACTION_SELECT_FROM_GALLERY)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            ACTION_CAPTURE_FROM_CAMERA -> {
                if (mCameraImageUri == null) {
                    return
                }
                imagePath = FileUtils.getPath(activity, mCameraImageUri!!)
                if (!TextUtils.isEmpty(imagePath)) {
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, mCameraImageUri)
                        imageGettingCallBack.ActivityResultData(bitmap, imagePath!!, File(imagePath!!))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                } else {
                    Toast.makeText(activity, "Invalid Image.", Toast.LENGTH_SHORT).show()
                }
            }
            ACTION_SELECT_FROM_GALLERY -> {
                if (data == null || data.data == null) {
                    return
                }
                imagePath = FileUtils.getPath(activity, data.data!!)
                if (!TextUtils.isEmpty(imagePath)) {
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, data.data)
                        imageGettingCallBack.ActivityResultData(bitmap, imagePath!!, File(imagePath!!))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                } else {
                    Toast.makeText(activity, "Invalid Image.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    interface ImageGettingCallBack {
        fun ActivityResultData(bitmap: Bitmap, path: String, file: File)

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
    }
}