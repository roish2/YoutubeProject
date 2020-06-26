package com.example.youtubeproject.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items (

	@SerializedName("kind") val kind : String?,
	@SerializedName("etag") val etag : String?,
	@SerializedName("id") val id : String?,
	@SerializedName("playlistItems") val playlistItems : PlaylistItems?,
	@SerializedName("snippet") val snippet : Snippet?,
	@SerializedName("contentDetails") val contentDetails : ContentDetails?
) : Parcelable