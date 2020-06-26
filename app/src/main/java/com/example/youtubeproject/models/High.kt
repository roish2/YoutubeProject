package com.example.youtubeproject.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class High (

	@SerializedName("url") val url : String?,
	@SerializedName("width") val width : Int?,
	@SerializedName("height") val height : Int?
) : Parcelable