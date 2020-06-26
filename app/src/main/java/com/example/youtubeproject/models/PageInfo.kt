package com.example.youtubeproject.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PageInfo (

	@SerializedName("totalResults") val totalResults : Int?,
	@SerializedName("resultsPerPage") val resultsPerPage : Int?
) : Parcelable