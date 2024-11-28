package com.zhadko.gifyviewer.extensions

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bumptech.glide.RequestManager

//class GlideImageLoader(
//    private val manager: RequestManager
//) : ImageLoader {
//
//    @SuppressLint("CheckResult")
//    override fun loadAsGif(link: String?, imageView: ImageView, placeholder: Int?) {
//        manager.asGif().load(link).apply {
//            if (placeholder != null) {
//                placeholder(placeholder)
//            }
//        }.into(imageView)
//    }
//}

fun RequestManager.loadAsGif(link: String?, imageView: ImageView, placeholder: Int?) {
    this.asGif().load(link).apply {
        if (placeholder != null) {
            placeholder(placeholder)
        }
    }.into(imageView)
}