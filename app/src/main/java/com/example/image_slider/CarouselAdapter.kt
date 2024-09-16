package com.example.image_slider

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.image_slider.databinding.EachItemBinding

class CarouselAdapter(private val imageList : MutableList<Int>) :
        RecyclerView.Adapter<CarouselAdapter.CarouselVIewHolder>(){



            inner class CarouselVIewHolder(private val binding: EachItemBinding)
                :RecyclerView.ViewHolder(binding.root){
                    fun bind(image : Int){
                        binding.imageView.setImageResource(image)
                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselVIewHolder {
        return CarouselVIewHolder(
            EachItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ))
    }

    override fun getItemCount(): Int {
      return imageList.size
    }

    override fun onBindViewHolder(holder: CarouselVIewHolder, position: Int) {
        holder.bind(imageList[position])
    }
}