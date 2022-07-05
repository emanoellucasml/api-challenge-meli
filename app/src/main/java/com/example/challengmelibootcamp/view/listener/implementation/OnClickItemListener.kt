package com.example.challengmelibootcamp.view.listener.implementation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.challengmelibootcamp.data.model.ProductModel
import com.example.challengmelibootcamp.utils.Constants
import com.example.challengmelibootcamp.view.activity.ProductDetailsActivity
import com.example.challengmelibootcamp.view.listener.`interface`.IOnClickItemListener

class OnClickItemListener(var owner: AppCompatActivity): IOnClickItemListener {
    override fun action(product: ProductModel) {
        val intent = Intent(owner, ProductDetailsActivity::class.java)
        intent.putExtra(Constants.PRODUCT.PRODUCT_ID, product.id)
        owner.startActivity(intent)
    }
}