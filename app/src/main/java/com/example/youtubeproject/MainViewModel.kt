package com.example.youtubeproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeproject.models.ExpendedListData
import com.example.youtubeproject.models.HeaderListData
import com.example.youtubeproject.models.JsonRootResponse
import com.example.youtubeproject.network.RetrofitClient
import com.example.youtubeproject.network.YoutubeInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel: ViewModel() {

    private var listCall: Call<JsonRootResponse?>?
    private val headerData:ArrayList<HeaderListData> = ArrayList()
    private val childData: HashMap<String, ArrayList<ExpendedListData>> = HashMap()
    var steps = MutableLiveData<Steps>()

    init {
        val retrofitClient: Retrofit?  = RetrofitClient.createClient()
        val service : YoutubeInterface? = retrofitClient?.create(YoutubeInterface::class.java)
        listCall = service?.getYoutubeList()
    }

    fun getPlaylists(){
        listCall?.enqueue(object : Callback<JsonRootResponse?> {
            override fun onResponse(call: Call<JsonRootResponse?>, response: Response<JsonRootResponse?>) {
                createData(response.body())
            }

            override fun onFailure(call: Call<JsonRootResponse?>, t: Throwable) {

            }

        })
    }

    private fun createData(response: JsonRootResponse?) {

        response?.let {
            it.items?.onEach {headItem ->
                val childAdapterData : ArrayList<ExpendedListData> = ArrayList<ExpendedListData>()
                var child: ExpendedListData
                headItem.snippet?.publishedAt
                val date:String = getFormattedDate(headItem.snippet?.publishedAt)
                val headerListData: HeaderListData = HeaderListData(headItem.id, headItem.snippet?.title, date, headItem.snippet?.thumbnails?.default?.url, headItem.snippet?.channelTitle)
                headerData.add(headerListData)

                headItem.playlistItems?.items?.onEach {playList->
                    val videoDate = getFormattedDate(playList.contentDetails.videoPublishedAt)
                    child = ExpendedListData(playList.videoSnippet.title, videoDate, playList.videoSnippet.description, playList.videoSnippet.resourceId?.videoId)
                    childAdapterData.add(child)
                }
                headItem.id?.let {headerId->
                    childData.put(headerId, childAdapterData)
                }
            }
        }
        steps.value = Steps.DataReady(headerData, childData)
    }

    //method to convert the input dateFormat to an more convention format
    private fun getFormattedDate(defaultDate:String?):String{
        val inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val outputPattern = "dd/MM/YYYY"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var tempDate: Date?
        var date:String = ""

        try {
            defaultDate?.let {
                tempDate = inputFormat.parse(it)
                tempDate?.let {
                    date = outputFormat.format(it)
                }
            }
        }catch (e :Exception){
            date = ""
        }

        return date
    }

    //class that contain the flow's steps
    //can add steps as necessary
    sealed class Steps(){
        class DataReady(val headerData:ArrayList<HeaderListData>,val childData: HashMap<String, ArrayList<ExpendedListData>>) :Steps()
        object OnError :Steps()
    }
}