package com.example.challengmelibootcamp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengmelibootcamp.data.model.ProductModel
import com.example.challengmelibootcamp.databinding.ItemRowBinding
import com.example.challengmelibootcamp.view.listener.`interface`.OnClickItemListener
import com.example.challengmelibootcamp.view.viewholder.ProductViewHolder

class ProductsAdapter(var products: List<ProductModel>, var context: Context, var onClickItemListener: OnClickItemListener) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        return holder.bind(products[position], onClickItemListener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    public fun updateProducts(products: List<ProductModel>){
        this.products = products
        notifyDataSetChanged()
    }
}