package com.example.wanandroid_kt.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    var id: Long,
    var articleId: Long,
    var name: String?,
    var url: String?
) : Parcelable