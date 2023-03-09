package com.affan.finalproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(
    var name:String?,
    var director:String?,
    var releaseDate:String?,
    var desciption:String?,

    var photo:String?
):Parcelable
