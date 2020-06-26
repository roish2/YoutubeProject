package com.example.youtubeproject.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.youtubeproject.R
import com.example.youtubeproject.models.ExpendedListData
import com.example.youtubeproject.models.HeaderListData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MyExpandableListAdapter(val activity: AppCompatActivity, val context:Context, private val headerData:ArrayList<HeaderListData>, private val childData: HashMap<String, ArrayList<ExpendedListData>>): BaseExpandableListAdapter() {


    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val title = (getGroup(groupPosition) as HeaderListData).title
        val data = (getGroup(groupPosition) as HeaderListData).date

        var layout = convertView

        if (layout == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            layout = inflater.inflate(R.layout.youtube_list_header_item, parent, false)
        }

        layout?.let {myLayout->
            var titleText:TextView = myLayout.findViewById(R.id.textView)
            var dateText:TextView = myLayout.findViewById(R.id.textView2)
            titleText.text = title
            dateText.text = data
        }

        return layout!!
    }


    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val currentChild = (getChild(groupPosition, childPosition) as ExpendedListData)

        val title = currentChild.title
        val data = currentChild.date

        var mylayout:View? = convertView

        if (mylayout == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            mylayout = inflater.inflate(R.layout.youtube_list_expand_item, null)
            val video:MutableLiveData<String> = MutableLiveData<String>()

            mylayout?.setTag(video)
        }

        mylayout?.let { myLayout->
            val titleText:TextView = myLayout.findViewById(R.id.textView3)
            val dateText:TextView = myLayout.findViewById(R.id.textView4)
            val videoPlayer:YouTubePlayerView = myLayout.findViewById(R.id.video_player)
            val video :MutableLiveData<String> =  mylayout.getTag() as MutableLiveData<String>

            video.value =currentChild.videoId
//            video.value = VideoPlayData(currentChild.videoId,0f)
            titleText.text = title
            dateText.text = data

            videoPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    video.observe(activity, Observer {
                        youTubePlayer.cueVideo(it,0f)
                    })
                }


            })

        }

        return mylayout!!
    }


    override fun getChildrenCount(groupPosition: Int): Int {
        childData[headerData[groupPosition].headerId]?.let {
        return it.size
       }?: kotlin.run {
            return 0
        }

    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any? {
       return  childData[headerData[groupPosition].headerId]?.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
       return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return headerData.size
    }
    override fun getGroup(groupPosition: Int): Any {
        return headerData[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }
}