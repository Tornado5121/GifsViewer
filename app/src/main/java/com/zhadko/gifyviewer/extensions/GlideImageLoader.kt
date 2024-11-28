package com.zhadko.gifyviewer.extensions

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bumptech.glide.RequestManager

@SuppressLint("CheckResult")
fun RequestManager.loadAsGif(link: String?, imageView: ImageView, placeholder: Int?) {
    this.asGif().load(link).apply {
        if (placeholder != null) {
            placeholder(placeholder)
        }
    }.into(imageView)
}