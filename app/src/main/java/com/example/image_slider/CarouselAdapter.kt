package com.example.image_slider

import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.image_slider.databinding.EachItemBinding
import com.bumptech.glide.Glide
class CarouselAdapter(private val imageList : MutableList<Any>) :
        RecyclerView.Adapter<CarouselAdapter.CarouselVIewHolder>(){



    inner class CarouselVIewHolder(itemView : View)
                :RecyclerView.ViewHolder(itemView){
                    fun bind(image : Any){
                            Glide.with(itemView.context)
                                .load(image)
                                .into(itemView.findViewById<ImageView>(R.id.imageView))
                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselVIewHolder {
        return CarouselVIewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false))
    }

    override fun getItemCount(): Int {
      return imageList.size * 100
    }

    override fun onBindViewHolder(holder: CarouselVIewHolder, position: Int) {
        val actualPosition = position % imageList.size
        holder.bind(imageList[actualPosition])
    }
}