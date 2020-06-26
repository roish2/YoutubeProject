package com.example.youtubeproject.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoContentDetails(@SerializedName("videoId")val videoId:String?,
                               @SerializedName("videoPublishedAt")val videoPublishedAt:String?) : Parcelable {
}