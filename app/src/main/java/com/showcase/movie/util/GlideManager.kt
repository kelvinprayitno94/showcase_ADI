package com.showcase.movie.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Dihardja Software on 2020-02-14.
 */
class AlertManager {

    companion object {
        private var instance: AlertManager? = null

        fun getInstance(): AlertManager {
            if (instance == null) {
                instance =
                    AlertManager()
            }
            return instance!!
        }
    }

    fun LoadImageCircleCrop(
        context: Context?,
        imgUrl: String?,
        target: ImageView?
    ) {
        Glide.with(context!!)
            .load(imgUrl)
            .apply(RequestOptions.circleCropTransform())
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .error(R.drawable.image_402)
            .into(target)
    }

    fun LoadImageCenterCrop(
        context: Context?,
        imgUrl: String?,
        target: ImageView?
    ) {
        Glide.with(context!!)
            .load(imgUrl)
            .apply(RequestOptions.centerCropTransform())
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .error(R.drawable.image_402)
            .into(target)
    }
}