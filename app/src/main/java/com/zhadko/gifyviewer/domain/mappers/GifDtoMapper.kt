package com.zhadko.gifyviewer.domain.mappers

import com.zhadko.gifyviewer.data.network.models.Data
import com.zhadko.gifyviewer.domain.models.Gif

fun Data.asGif(): Gif {
    return Gif(title = title, path = images.original.url)
}

fun List<Data>.asGifList(): List<Gif> {
    return map {
        it.asGif()
    }
}