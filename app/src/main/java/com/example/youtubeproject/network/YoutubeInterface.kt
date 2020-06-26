package com.example.youtubeproject.network

import com.example.youtubeproject.models.JsonRootResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface YoutubeInterface {

    @GET("youtube/playlists.json")
    fun getYoutubeList(): Call<JsonRootResponse?>?
}