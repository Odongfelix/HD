package com.hd.resolution.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import com.hd.R
import kotlin.math.roundToInt


fun Bitmap.resolution(): Resolution {
    //compute resolution
    return Resolution.HD
}

fun Context.string(id: Int): String {
    return getString(id)
}

fun Context.selectPhotosString(): String {
    return string(R.string.select_photo)
}

fun Resolution.icon(): Int {
    return when (this) {
        Resolution.HD -> R.drawable.hd_24
        Resolution.HD_PLUS -> R.drawable.full_hd_24
        Resolution.FOUR_K -> R.drawable.four
    }
}

fun Resolution.dimension(): Point {
    return when (this) {
        Resolution.HD -> Point(1280, 720)
        Resolution.HD_PLUS -> Point(1920, 1080)
        Resolution.FOUR_K -> Point(3840, 2160)
    }
}

fun Resolution.size(): Float {
    val dimen = dimension()
    val size = (dimen.x * dimen.y.toFloat()) / (1024 * 1024)
    return size
}