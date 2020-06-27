package com.example.youtubeproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeproject.models.ChildListData
import com.example.youtubeproject.models.JsonRootResponse
import com.example.youtubeproject.models.RecyclerHeaderData
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
    private val headerRecyclerData :ArrayList<RecyclerHeaderData> = ArrayList()
    private val childData: HashMap<String, ArrayList<ChildListData>> = HashMap()
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
                val childAdapterData : ArrayList<ChildListData> = ArrayList<ChildListData>()
                var child: ChildListData
                headItem.snippet?.publishedAt
                val date:String = getFormattedDate(headItem.snippet?.publishedAt)


                headItem.playlistItems?.items?.onEach {playList->
                    val videoDate = getFormattedDate(playList.contentDetails.videoPublishedAt)
                    child = ChildListData(playList.videoSnippet.title, videoDate, playList.videoSnippet.description, playList.videoSnippet.resourceId?.videoId)
                    childAdapterData.add(child)
                }
                headItem.id?.let {headerId->
                    childData.put(headerId, childAdapterData)
                }

                val headerRecyclerItem = RecyclerHeaderData(headItem.id, headItem.snippet?.title, date, headItem.snippet?.thumbnails?.default?.url, headItem.snippet?.channelTitle, childAdapterData)

                headerRecyclerData.add(headerRecyclerItem)

            }
        }
        steps.value = Steps.DataReady(headerRecyclerData)
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
        class DataReady(val headerRecyclerData: ArrayList<RecyclerHeaderData>) :Steps()
        object OnError :Steps()
    }
}