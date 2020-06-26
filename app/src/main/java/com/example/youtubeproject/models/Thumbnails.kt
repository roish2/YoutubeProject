package com.example.youtubeproject.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnails (

	@SerializedName("default") val default : Default?,
	@SerializedName("medium") val medium : Medium?,
	@SerializedName("high") val high : High?,
	@SerializedName("standard") val standard : Standard?,
	@SerializedName("maxres") val maxres : Maxres?
) : Parcelable