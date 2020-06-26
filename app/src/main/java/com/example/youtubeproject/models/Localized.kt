package com.example.youtubeproject.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Localized (

	@SerializedName("title") val title : String?,
	@SerializedName("description") val description : String?
) : Parcelable