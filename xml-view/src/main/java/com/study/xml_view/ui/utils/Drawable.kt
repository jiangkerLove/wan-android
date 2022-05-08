package com.study.xml_view.ui.utils

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.annotation.ColorInt
import com.study.xml_view.App


fun getBgDrawable(@ColorInt color: Long, radius: Int): Drawable {
    val drawable = GradientDrawable()
    drawable.shape = GradientDrawable.RECTANGLE
    drawable.setColor(color.toInt())
    drawable.cornerRadius = radius.toFloat()
    return drawable
}

fun TextView.setTextColor(color: Long) {
    setTextColor(color.toInt())
}

fun Int.dp(): Int {
    val density = App.ctx.resources.displayMetrics.density
    return (density * this + 0.5F).toInt()
}