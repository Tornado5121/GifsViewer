package com.zhadko.gifyviewer.common

import android.widget.ImageView

interface ImageLoader {

    fun loadAsGif(link: String?, imageView: ImageView, placeholder: Int?)
}