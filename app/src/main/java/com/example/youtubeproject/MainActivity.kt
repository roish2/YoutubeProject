package com.example.youtubeproject

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.youtubeproject.ui.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var viewModel :MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.steps.observe(this, nameObserver)

        viewModel.getPlaylists()

    }

   private val nameObserver = Observer<MainViewModel.Steps> { classs ->
        when(classs){
            is MainViewModel.Steps.DataReady->{
                val newFragment: MainFragment = MainFragment.newInstance(classs.headerData, classs.childData)
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.fragment_container, newFragment).commit()
            }
            is MainViewModel.Steps.OnError->{

            }
        }}
}