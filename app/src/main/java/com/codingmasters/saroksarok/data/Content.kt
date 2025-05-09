package com.codingmasters.saroksarok.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content (
    val image:Int,
    val title:String,
    val id:String,
    val price:String,
    val type:String,
    val description:String,
):Parcelable