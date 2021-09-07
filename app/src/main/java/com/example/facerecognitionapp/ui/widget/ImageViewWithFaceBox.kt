package com.example.facerecognitionapp.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ImageView

class ImageViewWithFaceBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ImageView(context, attrs, defStyle) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val rects = mutableListOf<Rect>()

    init {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
    }

    fun setRect(rects: List<Rect>) {
        this.rects.clear()
        this.rects.addAll(rects)
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        rects.forEach { rect ->
            val x: Float = rect.centerX().toFloat()
            val y: Float = rect.centerY().toFloat()

            val xOffset = rect.width() / 2.0f
            val yOffset = rect.height() / 2.0f
            val left = x - xOffset
            val top = y - yOffset
            val right = x + xOffset
            val bottom = y + yOffset

            canvas!!.drawRect(left, top, right, bottom, paint)
        }
    }
}