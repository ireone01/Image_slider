package com.example.image_slider

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.image_slider.databinding.ActivityMainBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper

class MainActivity : AppCompatActivity(){
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.goku_001)
        imageList.add(R.drawable.goku_002)
        imageList.add(R.drawable.goku_003)
        imageList.add(R.drawable.goku_004)
        imageList.add(R.drawable.goku_005)
        imageList.add(R.drawable.goku_006)
        imageList.add(R.drawable.goku_007)

        val adapter = CarouselAdapter(imageList)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = CarouselLayoutManager()
        CarouselSnapHelper().attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.adapter = adapter

        val initialPosition = adapter.itemCount /2 - ( adapter.itemCount /2) % imageList.size
        binding.recyclerView.scrollToPosition(initialPosition)
        binding.recyclerView.setItemViewCacheSize(5)
        }
    }

