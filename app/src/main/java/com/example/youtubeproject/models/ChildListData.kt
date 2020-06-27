package com.example.youtubeproject.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChildListData(val title:String?, val date:String?, val description:String?, val videoId:String?) :
    Parcelable