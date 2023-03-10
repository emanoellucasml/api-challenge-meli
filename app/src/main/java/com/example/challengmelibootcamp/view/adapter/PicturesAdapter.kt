package com.example.challengmelibootcamp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.challengmelibootcamp.R
import com.example.challengmelibootcamp.data.model.ProductPictureModel
import com.example.challengmelibootcamp.databinding.ProductPictureSliderItemBinding

class PicturesAdapter(val pictures: List<ProductPictureModel>, val context: Context) : RecyclerView.Adapter<PicturesAdapter.PictureViewHolder>() {

    class PictureViewHolder(val binding: ProductPictureSliderItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding: ProductPictureSliderItemBinding = ProductPictureSliderItemBinding.inflate(LayoutInflater.from(context), parent,false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {

        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.icon_loading)
            .error(R.drawable.icon_test)

        Glide.with(context)
            .applyDefaultRequestOptions(options)
            .load(pictures.get(position).url)
            .into(holder.binding.imageviewSliderItem)
    }

    override fun getItemCount(): Int {
        return pictures.size
    }
}