package com.example.ytest.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.Transition
import timber.log.Timber

/**
 *
 *  BindingAdapters
 *
 *  - Glide를 사용한 이미지 적용을 위한 커스텀 바인딩 어댑터
 *
 */

object BindingAdapters {

    // 이미지 적용
    @BindingAdapter("setImageWithGlide")
    @JvmStatic fun setImageWithGlide(view: ImageView, imageUrl: String?) {

        imageUrl?.let {

            Glide.with(view.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .override(view.width, view.height)
                .into(view)
        }
    }
}