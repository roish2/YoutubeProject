package com.example.youtubeproject.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JsonRootResponse (

	@SerializedName("kind") val kind : String?,
	@SerializedName("etag") val etag : String?,
	@SerializedName("pageInfo") val pageInfo : PageInfo?,
	@SerializedName("items") val items : List<Items>?
) : Parcelable