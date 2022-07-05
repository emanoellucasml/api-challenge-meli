package com.example.challengmelibootcamp.view.listener.implementation

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challengmelibootcamp.data.model.ProductModel
import com.example.challengmelibootcamp.databinding.ActivityProductDetailsBinding
import com.example.challengmelibootcamp.view.activity.ProductDetailsActivity
import com.example.challengmelibootcamp.view.listener.`interface`.OnClickItemListener

class OnClickItemListenerImplementation(var owner: AppCompatActivity): OnClickItemListener {
    override fun action(product: ProductModel) {
        val intent = Intent(owner, ProductDetailsActivity::class.java)
        owner.startActivity(intent)
    }
}