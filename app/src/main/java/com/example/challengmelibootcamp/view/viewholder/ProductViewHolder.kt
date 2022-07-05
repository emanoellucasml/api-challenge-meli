package com.example.challengmelibootcamp.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.challengmelibootcamp.R
import com.example.challengmelibootcamp.data.model.ProductModel
import com.example.challengmelibootcamp.databinding.ItemRowBinding
import com.example.challengmelibootcamp.view.listener.`interface`.OnClickItemListener

class ProductViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductModel, listener: OnClickItemListener){
        binding.textTitle.text = product.title
        binding.textSubtitle.text = product.subtitle
        binding.textPrice.text = "${binding.textPrice.text}".replace("*", product.price).replace(".", ",")

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)

        Glide.with(binding.root)
            .applyDefaultRequestOptions(requestOptions)
            .load(product.thumbnail)
            .into(binding.imageThumbnail)

        binding.root.setOnClickListener {
            listener.action(product)
        }

    }
}