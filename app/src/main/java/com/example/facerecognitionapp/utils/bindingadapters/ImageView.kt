package com.example.facerecognitionapp.utils.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(
    value = ["imageRes"]
)
fun ImageView.setImageRes(
    res: Int?
) {
    if (res == null) return

    Glide.with(this)
        .load(res)
        .into(this)
}