package com.holidaypirates.utils

import android.app.Activity
import android.util.DisplayMetrics
import android.widget.ImageView
import com.app.facts.R
import com.bumptech.glide.Glide

/**
 * ImageUtils : Used for Image loading with Glide library
 */
object ImageUtils {

    const val EXTRA_PADDING = 30;
    fun loadImage(imageUrl: String?, imageView: ImageView?) {

        if (imageUrl.isNullOrEmpty()) {
            return
        }

        Glide.with(imageView?.context)
                .load(imageUrl)
                .centerCrop()
                .crossFade()
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder_error)
                .into(imageView)
    }

    fun getScreenWidth(context: Activity): Int {
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun imageHeight(context: Activity, columns: Int): Int {
        return (getScreenWidth(context) / columns).minus(EXTRA_PADDING)
    }
}