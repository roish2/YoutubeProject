package com.example.youtubeproject.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeproject.R
import com.example.youtubeproject.models.RecyclerHeaderData

class RecyclerViewAdapter (val context: Context, val headerItems: ArrayList<RecyclerHeaderData>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.youtube_list_header_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return headerItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val headerItem = headerItems[position]

        holder.bind(headerItem)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener  {
        private lateinit var childContainer:LinearLayout

        fun bind(headerData: RecyclerHeaderData){
            val title = headerData.title
            val date = headerData.date

            val intMaxNoOfChild = headerData.childData.size
            val mainLayout :ConstraintLayout = itemView.findViewById(R.id.main_header_layout)
            val channelImage:ImageView = itemView.findViewById(R.id.playlist_image)
            childContainer = itemView.findViewById(R.id.child_container)

            var titleText: TextView = itemView.findViewById(R.id.playlist_title)
            var dateText: TextView = itemView.findViewById(R.id.playlist_date)

            titleText.text = title
            dateText.text = date

            Glide
                .with(context)
                .load(headerData.thumbnails)
                .centerCrop()
                .into(channelImage);

            mainLayout.setOnClickListener(this)

            //add the childItem to the header view
            for (indexView in 0 until intMaxNoOfChild) {
                val youtubeCustomView = YoutubeCustomView(context)
                youtubeCustomView.setParams(headerData.childData[indexView])
                childContainer.addView(youtubeCustomView)
            }
        }

        override fun onClick(view: View?) {
            when(view?.id){
                //show or hide childItems
                R.id.main_header_layout ->{
                    if (childContainer.visibility == View.VISIBLE) {
                        childContainer.visibility = View.GONE
                    } else {
                        childContainer.visibility = View.VISIBLE
                    }
                }
            }

        }
    }
}