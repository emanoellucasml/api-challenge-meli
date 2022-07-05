package com.example.challengmelibootcamp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Option
import com.bumptech.glide.request.RequestOptions
import com.example.challengmelibootcamp.R
import com.example.challengmelibootcamp.data.model.ProductDescriptionModel
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

        setListeners()
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
                setProductsData(viewModel.productDescriptionModel())

            }else{
                Toast.makeText(baseContext, "ERRO!!!!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setProductsData(productDescriptionModel: ProductDescriptionModel){
        binding.textProductTitle.text = productDescriptionModel.productModel.title
        binding.textUnitsSold.text = productDescriptionModel.productModel.soldQuantity + " vendidos"
        binding.textProductPrice.text = "${binding.textProductPrice.text}".replace("*", productDescriptionModel.productModel.price)
        binding.textAvailableQuantity.text = "${binding.textAvailableQuantity.text}".replace("*", productDescriptionModel.productModel.availableQuantity)
        binding.textDescription.text = productDescriptionModel.description

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(productDescriptionModel.productModel.pictures.first().url)
            .into(binding.imageProductThumbnail)
    }

    private fun setListeners(){
        this.binding.buttonBack.setOnClickListener {
            this.finish()
        }
    }

}