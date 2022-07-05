package com.example.challengmelibootcamp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengmelibootcamp.data.model.ProductDescriptionModel
import com.example.challengmelibootcamp.data.model.ProductModel
import com.example.challengmelibootcamp.data.repository.ProductRepository
import com.example.challengmelibootcamp.utils.Constants
import com.example.challengmelibootcamp.utils.ProductsSearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.ConnectException

class ProductDetailsViewModel : ViewModel(){
    private val productRepository: ProductRepository = ProductRepository()

    private var productsSearchResult: MutableLiveData<ProductsSearchResult> = MutableLiveData()
    public fun productsSearchResult() : LiveData<ProductsSearchResult>{
        return this.productsSearchResult
    }

    private var productDescriptionModel: ProductDescriptionModel = ProductDescriptionModel()
    public fun productDescriptionModel() : ProductDescriptionModel {
        return this.productDescriptionModel
    }

    public fun getProductDetails(id: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val result = productRepository.getDetails(id)
                if (result.code() == Constants.HTTP.SUCCESS) {
                    val product: ProductModel?  = result.body()?.first()?.body
                    if(product != null){
                        getProductDescription(product)
                    }else{
                        productsSearchResult.postValue(ProductsSearchResult(false, Constants.MESSAGE.UKNOWN_ERROR))
                    }
                } else {
                    productsSearchResult.postValue(ProductsSearchResult(false, Constants.MESSAGE.UKNOWN_ERROR))
                }
            }catch (e: ConnectException){
                productsSearchResult.postValue(ProductsSearchResult(false, Constants.MESSAGE.INTERNET_CONNECTION))
            }catch (e: IOException){
                productsSearchResult.postValue(ProductsSearchResult(false, Constants.MESSAGE.UKNOWN_ERROR))
            }
        }
    }

    public fun getProductDescription(productModel: ProductModel){
        val result = productRepository.getDescription(productModel.id)
        if(result.code() == Constants.HTTP.SUCCESS){
            this.productDescriptionModel = result.body()!!
            this.productDescriptionModel.productModel = productModel
            productsSearchResult.postValue(ProductsSearchResult(true, "Sucesso"))
        }else{
            productsSearchResult.postValue(ProductsSearchResult(false, Constants.MESSAGE.UKNOWN_ERROR))
        }
    }

}