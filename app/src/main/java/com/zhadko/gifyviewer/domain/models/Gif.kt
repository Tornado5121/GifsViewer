package com.zhadko.gifyviewer.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Gif(
    val title: String,
    val path: String
) : Parcelable