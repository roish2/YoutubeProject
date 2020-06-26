package com.example.youtubeproject.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoSnippetItem(@SerializedName("publishedAt") val publishedAt:String?,
                            @SerializedName("channelId") val channelId:String?,
                            @SerializedName("title") val title:String?,
                            @SerializedName("description")val description:String?,
                            @SerializedName("thumbnails") val thumbnails: Thumbnails?,
                            @SerializedName("resourceId") val resourceId:ResourceId?) : Parcelable {
}