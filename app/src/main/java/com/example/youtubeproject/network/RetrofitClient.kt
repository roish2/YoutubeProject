package com.example.youtubeproject.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        private var retrofit:Retrofit?= null
        const val baseUrl:String = "https://landing.cal-online.co.il/"

        fun createClient():Retrofit?{

            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }
    }
}