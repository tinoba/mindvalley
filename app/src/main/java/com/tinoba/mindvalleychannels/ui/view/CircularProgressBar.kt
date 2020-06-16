package com.tinoba.mindvalleychannels.ui.view

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

class CircularProgressBar(context: Context) : CircularProgressDrawable(context) {

    companion object{

        private const val STROKE_WIDTH = 5f
        private const val CORNER_RADIUS = 30f
    }

    init {
        strokeWidth = STROKE_WIDTH
        centerRadius = CORNER_RADIUS
        start()
    }
}