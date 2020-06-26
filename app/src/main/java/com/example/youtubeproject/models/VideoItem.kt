package com.example.youtubeproject.models

import android.os.Parcelable
import com.example.youtubeproject.models.VideoContentDetails
import com.example.youtubeproject.models.VideoSnippetItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoItem(@SerializedName("kind")val kind:String,
                     @SerializedName("etag")val etag:String,
                     @SerializedName("id")val id:String,
                     @SerializedName("snippet")val videoSnippet: VideoSnippetItem,
                     @SerializedName("contentDetails")val contentDetails: VideoContentDetails
) :
    Parcelable {
}