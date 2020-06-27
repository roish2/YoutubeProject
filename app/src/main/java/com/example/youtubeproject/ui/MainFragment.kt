package com.example.youtubeproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeproject.R
import com.example.youtubeproject.models.RecyclerHeaderData

class MainFragment: Fragment() {
    private var headerRecyclerData:ArrayList<RecyclerHeaderData> = ArrayList()

    companion object{
        const val R_HEADER_DATA = "r_header_data"
        fun newInstance(headerRecyclerData:ArrayList<RecyclerHeaderData>):MainFragment{
            val fragment:MainFragment = MainFragment()
            val bundle = Bundle()

            bundle.putParcelableArrayList(R_HEADER_DATA, headerRecyclerData)

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.main_fragment_layout, container, false)
        val recyclerView :RecyclerView= layout.findViewById(R.id.my_recyclerView)

        arguments?.let {

            headerRecyclerData = it.getParcelableArrayList<RecyclerHeaderData>(R_HEADER_DATA) as ArrayList<RecyclerHeaderData>
        }

        val recyclerDataAdapter = RecyclerViewAdapter(context!!, headerRecyclerData)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerDataAdapter



        return layout
    }

}