package com.example.youtubeproject.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentDetails (

	@SerializedName("itemCount") val itemCount : Int?
) : Parcelable