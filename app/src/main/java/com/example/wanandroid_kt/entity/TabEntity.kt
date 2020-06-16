package com.example.wanandroid_kt.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by xiaojianjun on 2019-11-12.
 */
@Parcelize
data class TabEntity(
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int,
    val children: ArrayList<TabEntity>
) : Parcelable