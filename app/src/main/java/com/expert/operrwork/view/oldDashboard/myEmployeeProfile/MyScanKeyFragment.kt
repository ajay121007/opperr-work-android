package com.expert.operrwork.view.oldDashboard.myEmployeeProfile

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.expert.operrwork.R
import com.expert.operrwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_scan_key.*


/**
 * Created by Akshay.
 */
class MyScanKeyFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_scan_key, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        val mMultiFormatWriter = MultiFormatWriter()
        val mBitMatrix: BitMatrix = mMultiFormatWriter.encode("Test", BarcodeFormat.QR_CODE, 550, 550)
        val mBarcodeEncoder = BarcodeEncoder()
        val bitmap: Bitmap = mBarcodeEncoder.createBitmap(mBitMatrix)
        img_qr_code.setImageBitmap(bitmap)
    }
}