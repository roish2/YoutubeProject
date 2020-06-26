package com.example.youtubeproject.models

import com.google.gson.annotations.SerializedName

data class VideoPlaylistItem (
    @SerializedName("videoId") val videoId:String?,
    @SerializedName("videoPublishedAt") val videoPublishedAt:String?
){

}