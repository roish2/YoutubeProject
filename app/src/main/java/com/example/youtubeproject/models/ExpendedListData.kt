package com.example.youtubeproject.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExpendedListData(val title:String?, val date:String?,val description:String?, val videoId:String?) :
    Parcelable