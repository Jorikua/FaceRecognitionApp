package com.example.facerecognitionapp.utils.bindingadapters

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.facerecognitionapp.utils.extensions.dpToPx

@BindingAdapter("android:visibility")
fun View.setVisibility(value: Boolean) {
    isVisible = value
}

@BindingAdapter(
    value = [
        "marginStart",
        "marginTop",
        "marginEnd",
        "marginBottom"
    ],
    requireAll = false
)
fun View.bindMargins(
    start: Int?, top: Int?, end: Int?, bottom: Int?
) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams ?: return

    with(lp) {
        if (start != null) marginStart = context.dpToPx(start)
        if (end != null) lp.marginEnd = context.dpToPx(end)

        if (top != null) topMargin = context.dpToPx(top)
        if (bottom != null) bottomMargin = context.dpToPx(bottom)
    }
    layoutParams = lp
}