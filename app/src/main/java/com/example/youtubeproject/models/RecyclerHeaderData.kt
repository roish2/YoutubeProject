package com.example.youtubeproject.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecyclerHeaderData(val headerId:String?, val title:String?, val date:String?, val thumbnails:String?, val channelTitle:String?, val childData:ArrayList<ChildListData>) :
    Parcelable {
}