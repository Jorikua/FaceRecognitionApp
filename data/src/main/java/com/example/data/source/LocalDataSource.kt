package com.example.data.source

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.data.R

class LocalDataSource(
    private val context: Context
) {

    val resources = listOf(
        R.drawable.nick,
        R.drawable.android,
        R.drawable.larry,
        R.drawable.rich,
        R.drawable.sergey,
        R.drawable.sundar,
        R.drawable.andy
    )

    fun getImagesBitmap(): List<Bitmap> {
        return resources.map {
            Glide.with(context)
                .asBitmap()
                .load(it)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .submit().get()
        }
    }

    fun getImageBitmap(imageRes: Int): Bitmap {
        return Glide.with(context)
            .asBitmap()
            .load(imageRes)
            .submit().get()
    }
}