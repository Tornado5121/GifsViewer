package com.zhadko.gifyviewer.data.network.models

import com.zhadko.gifyviewer.domain.models.Gif

data class Data(
    val images: Images,
    val title: String,
)

fun Data.asGif(): Gif {
    return Gif(title = title, path = images.original.url)
}

fun List<Data>.asGifList(): List<Gif> {
    return map {
        it.asGif()
    }
}