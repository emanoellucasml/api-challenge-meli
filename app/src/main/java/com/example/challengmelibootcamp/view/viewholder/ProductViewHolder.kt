package com.example.challengmelibootcamp.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.challengmelibootcamp.data.model.ProductModel
import com.example.challengmelibootcamp.databinding.ItemRowBinding

class ProductViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductModel){
        binding.textTitle.text = product.title
    }
}