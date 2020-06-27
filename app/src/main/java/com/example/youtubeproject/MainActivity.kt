package com.example.youtubeproject

import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.youtubeproject.ui.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var viewModel :MainViewModel
    lateinit var progressBar :ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        progressBar = findViewById(R.id.progress_bar)

        viewModel.steps.observe(this, nameObserver)

        viewModel.getPlaylists()

    }

   private val nameObserver = Observer<MainViewModel.Steps> { step ->
        when(step){
            is MainViewModel.Steps.DataReady->{
                val newFragment: MainFragment = MainFragment.newInstance(step.headerRecyclerData)
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.fragment_container, newFragment).commit()
                progressBar.visibility = View.GONE
            }
            is MainViewModel.Steps.OnError->{

            }
        }}
}