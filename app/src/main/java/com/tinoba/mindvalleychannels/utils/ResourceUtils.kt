package com.tinoba.mindvalleychannels.utils

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes

interface ResourceUtils {

    fun getColor(@ColorRes colorId: Int): Int

    fun getString(stringId: Int): String

    fun getString(stringId: Int, vararg args: Any): String

    fun getString(resourceName: String): String

    fun getString(resourceName: String, vararg args: Any): String

    fun getQuantityString(stringId: Int, quantity: Int): String

    fun getQuantityString(stringId: Int, quantity: Int, vararg args: Any): String

    fun getDrawable(@DrawableRes resourceId: Int): Drawable

    fun getDimensionPixelSizeFromResource(@DimenRes dimenResource: Int): Int
}