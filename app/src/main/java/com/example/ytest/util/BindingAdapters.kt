package com.example.ytest.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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
    @JvmStatic fun setImageWithGlide(view: ImageView, imageUrl: String) {
        if (imageUrl.isNotEmpty()) {
            Timber.tag("bindingAdapter").d("setting image")

            Glide.with(view.context)
                .load(imageUrl)
                .fitCenter()
                .into(view)
        }
    }
}