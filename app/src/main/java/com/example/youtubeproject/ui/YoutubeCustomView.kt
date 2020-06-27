package com.example.youtubeproject.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.youtubeproject.R
import com.example.youtubeproject.models.ChildListData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubeCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle){

    var titleText: TextView
    var dateText: TextView
    var videoPlayer: YouTubePlayerView


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myLayout  = inflater.inflate(R.layout.youtube_list_expand_item, this, true)

        titleText= myLayout.findViewById(R.id.textView3)
        dateText = myLayout.findViewById(R.id.textView4)
        videoPlayer = myLayout.findViewById(R.id.video_player)

    }

    fun setParams(currentChild: ChildListData){
        val title = currentChild.title
        val data = currentChild.date
        val video : MutableLiveData<String> =  MutableLiveData<String>()

        video.value = currentChild.videoId
//            video.value = VideoPlayData(currentChild.videoId,0f)
        titleText.text = title
        dateText.text = data

        videoPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                video.observe(context as LifecycleOwner, Observer {
                    youTubePlayer.cueVideo(it,0f)
                })
            }


        })
    }
}

