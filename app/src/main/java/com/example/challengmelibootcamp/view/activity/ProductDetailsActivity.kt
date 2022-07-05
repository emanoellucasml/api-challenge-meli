package com.example.challengmelibootcamp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.challengmelibootcamp.R
import com.example.challengmelibootcamp.databinding.ActivityProductDetailsBinding
import com.example.challengmelibootcamp.utils.Constants
import com.example.challengmelibootcamp.viewmodel.ProductDetailsViewModel

class ProductDetailsActivity : AppCompatActivity() {

    private val viewModel: ProductDetailsViewModel = ProductDetailsViewModel()
    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setObservers()

        searchProduct()
    }


    private fun searchProduct() : Unit{
        val productId: String? = intent.getStringExtra(Constants.PRODUCT.PRODUCT_ID)
        if(productId == null){
            finish()
            return kotlin.Unit
        }

        viewModel.getProductDetails(productId)
    }


    private fun setObservers(){
        viewModel.productsSearchResult().observe(this, Observer {
            if(it.success()){
                binding.textProductTitle.text = viewModel.productDescriptionModel().description
            }else{
                Toast.makeText(baseContext, "ERRO!!!!", Toast.LENGTH_LONG).show()
            }
        })
    }

}