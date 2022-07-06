package com.example.challengmelibootcamp.view.activity

import android.graphics.Picture
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
import com.example.challengmelibootcamp.infraestructure.Preferences
import com.example.challengmelibootcamp.utils.Constants
import com.example.challengmelibootcamp.view.adapter.PicturesAdapter
import com.example.challengmelibootcamp.viewmodel.ProductDetailsViewModel

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var adapter: PicturesAdapter
    private val viewModel: ProductDetailsViewModel by lazy {
        ProductDetailsViewModel(this.baseContext)
    }
    private lateinit var binding: ActivityProductDetailsBinding
    private val idProduct: String? by lazy {
        intent.getStringExtra(Constants.PRODUCT.PRODUCT_ID)
    }

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
                this.finish()
                Toast.makeText(baseContext, "Erro", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setProductsData(productDescriptionModel: ProductDescriptionModel){
        binding.textProductTitle.text = productDescriptionModel.productModel.title
        binding.textUnitsSold.text = productDescriptionModel.productModel.soldQuantity + " vendidos"
        binding.textProductPrice.text = "${binding.textProductPrice.text}".replace("*", productDescriptionModel.productModel.price)
        binding.textAvailableQuantity.text = "${binding.textAvailableQuantity.text}".replace("*", productDescriptionModel.productModel.availableQuantity)
        binding.textDescription.text = productDescriptionModel.description

        if(viewModel.isFavorite(productDescriptionModel.productModel.id)){
            binding.buttonFavorite.setBackgroundResource(R.drawable.icon_heart_full)
        }else{
            binding.buttonFavorite.setBackgroundResource(R.drawable.icon_heart_empty)
        }

        adapter = PicturesAdapter(productDescriptionModel.productModel.pictures, this)
        binding.imageProductThumbnail.adapter = adapter

    }

    private fun setListeners(){
        this.binding.buttonBack.setOnClickListener {
            this.finish()
        }

        this.binding.buttonFavorite.setOnClickListener {
            if(viewModel.isFavorite(this.idProduct!!)){
                binding.buttonFavorite.setBackgroundResource(R.drawable.icon_heart_empty)
                viewModel.disfavorProduct(this.idProduct!!)
                Toast.makeText(this, "Produto removido dos favoritos.", Toast.LENGTH_SHORT).show()
            }else{
                binding.buttonFavorite.setBackgroundResource(R.drawable.icon_heart_full)
                viewModel.favoriteProduct(this.idProduct!!)
                Toast.makeText(this, "Produto adicionado aos favoritos.", Toast.LENGTH_SHORT).show()
            }
        }
    }



}