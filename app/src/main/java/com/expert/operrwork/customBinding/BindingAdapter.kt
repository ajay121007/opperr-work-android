package com.expert.operrwork.customBinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {

    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter("dateFromtimeStamp")
fun bindTextFromTimeStamp(view: TextView,timestap:Long){
        val formatter = SimpleDateFormat("dd/MM/yyyy , h:mm a");
        val  dateString = formatter.format(Date(timestap))
        view.setText(dateString);
}