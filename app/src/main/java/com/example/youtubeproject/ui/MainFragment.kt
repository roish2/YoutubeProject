package com.example.youtubeproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import com.example.youtubeproject.MainActivity
import com.example.youtubeproject.R
import com.example.youtubeproject.models.ExpendedListData
import com.example.youtubeproject.models.HeaderListData

class MainFragment: Fragment() {
    private var headerData:ArrayList<HeaderListData> = ArrayList()
    private var childData: HashMap<String, ArrayList<ExpendedListData>> = HashMap()

    companion object{
        const val HEADER_DATA = "header_data"
        const val CHILD_DATA = "child_data"
        fun newInstance(header: ArrayList<HeaderListData>,child: HashMap<String, ArrayList<ExpendedListData>>):MainFragment{
            val fragment:MainFragment = MainFragment()
            val bundle = Bundle()

            bundle.putParcelableArrayList(HEADER_DATA, header)
            bundle.putSerializable(CHILD_DATA, child)

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.main_fragment_layout, container, false)
        val listView: ExpandableListView = layout.findViewById(R.id.main_list_view)

        arguments?.let {
            headerData = it.getParcelableArrayList<HeaderListData>(HEADER_DATA) as ArrayList<HeaderListData>
            childData = it.getSerializable(CHILD_DATA) as HashMap<String, ArrayList<ExpendedListData>>
        }

        val adapter:MyExpandableListAdapter = MyExpandableListAdapter(activity as MainActivity, context!!, headerData, childData)
        listView.setAdapter(adapter)


        return layout
    }

}