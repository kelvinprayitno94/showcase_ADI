package com.showcase.movie.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hino.hearts.R

/**
 * Created by Dihardja Software on 2020-02-14.
 */
class GlideManager {

    companion object {
        private var instance: GlideManager? = null

        fun getInstance(): GlideManager {
            if (instance == null) {
                instance =
                    GlideManager()
            }
            return instance!!
        }
    }

    fun LoadImageCircleCrop(
        context: Context?,
        imgUrl: String?,
        target: ImageView
    ) {
        Glide.with(context!!)
            .load(imgUrl)
            .apply(RequestOptions.circleCropTransform())
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(target)
    }

    fun LoadImageCenterCrop(
        context: Context?,
        imgUrl: String?,
        target: ImageView
    ) {
        Glide.with(context!!)
            .load(imgUrl)
            .apply(RequestOptions.centerCropTransform())
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(target)
    }
}