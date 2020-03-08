package com.tsdreamdeveloper.app.utils.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.load(
    url: Any,
    withCenterCrop: Boolean = true,
    placeholder: Int = -1,
    onLoadEnd: OnLoadEnd? = null,
    roundedCorners: Int = 0
) {
    val requestOptions = RequestOptions().apply {
        if (placeholder != -1) {
            placeholder(placeholder)
        }
    }
    val centerTransformations = if (withCenterCrop) {
        CenterCrop()
    } else {
        CenterInside()
    }

    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .transform(centerTransformations, RoundedCorners(roundedCorners.toPx(context)))
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadEnd?.onEndLoad()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadEnd?.onEndLoad()
                return false
            }
        })
        .into(this)
}

interface OnLoadEnd {
    fun onEndLoad()
}