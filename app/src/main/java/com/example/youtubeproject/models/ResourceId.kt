package com.example.youtubeproject.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResourceId (
	@SerializedName("kind") val kind : String?,
	@SerializedName("videoId") val videoId : String?
) : Parcelable