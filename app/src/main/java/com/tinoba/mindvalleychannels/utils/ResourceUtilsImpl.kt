package com.tinoba.mindvalleychannels.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.NoSuchPropertyException
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat

class ResourceUtilsImpl(
    private val context: Context,
    private val resources: Resources
) : ResourceUtils {

    companion object {

        private const val KEY_STRING = "string"
    }

    override fun getColor(@ColorRes colorId: Int) = ContextCompat.getColor(context, colorId)

    override fun getString(stringId: Int): String {
        return resources.getString(stringId)
    }

    override fun getString(stringId: Int, vararg args: Any): String {
        return resources.getString(stringId, *args)
    }

    override fun getString(resourceName: String): String {
        val stringRes = getStringResourceId(resourceName)
        return if (stringRes > 0) {
            getString(stringRes)
        } else ""
    }

    override fun getString(resourceName: String, vararg args: Any): String {
        val stringRes = getStringResourceId(resourceName)
        return if (stringRes > 0) {
            getString(stringRes, *args)
        } else ""
    }

    override fun getQuantityString(stringId: Int, quantity: Int, vararg args: Any): String {
        return resources.getQuantityString(stringId, quantity, *args)
    }

    override fun getQuantityString(stringId: Int, quantity: Int): String {
        return resources.getQuantityString(stringId, quantity)
    }

    private fun getStringResourceId(name: String): Int {
        return resources.getIdentifier(name, KEY_STRING, context.packageName)
    }

    override fun getDrawable(resourceId: Int): Drawable {
        return ContextCompat.getDrawable(context, resourceId) ?: throw NoSuchPropertyException("No such drawable")
    }

    override fun getDimensionPixelSizeFromResource(@DimenRes dimenResource: Int): Int = resources.getDimensionPixelSize(dimenResource)
}