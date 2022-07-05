package com.example.challengmelibootcamp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengmelibootcamp.data.model.CategoryModel
import com.example.challengmelibootcamp.data.model.ProductDetailsModel
import com.example.challengmelibootcamp.data.model.ProductModel
import com.example.challengmelibootcamp.data.model.TopTwentyByCategoryModel
import com.example.challengmelibootcamp.data.repository.CategoryRepository
import com.example.challengmelibootcamp.data.repository.ProductRepository
import com.example.challengmelibootcamp.data.repository.TopTwentyByCategoryRepository
import com.example.challengmelibootcamp.utils.ProductsSearchResult
import com.example.challengmelibootcamp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.lang.StringBuilder
import java.net.ConnectException

class MainViewModel(val context: Context): ViewModel() {
    private val categoryRepository: CategoryRepository = CategoryRepository()
    private val topTwentyByCategoryRepository: TopTwentyByCategoryRepository = TopTwentyByCategoryRepository()
    private val productRepository: ProductRepository = ProductRepository()

    private var productsSearch: MutableLiveData<ProductsSearchResult> = MutableLiveData<ProductsSearchResult>()
    public fun productSearch() : LiveData<ProductsSearchResult> {
        return this.productsSearch
    }

    private var productsCollection: MutableList<ProductModel> = mutableListOf()
    public fun productsCollection() : List<ProductModel>{
        return this.productsCollection
    }

    private var topTwentyByCategory: MutableLiveData<TopTwentyByCategoryModel> = MutableLiveData()
    private fun topTwentyByCategory(): LiveData<TopTwentyByCategoryModel>{
        return this.topTwentyByCategory
    }


    public fun searchCategory(categoryName: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val categoryResponse: Response<List<CategoryModel>> = categoryRepository.searchCategory(categoryName)
                when (categoryResponse.code()) {
                    Constants.HTTP.SUCCESS -> {
                        val categoryId = categoryResponse.body()?.first()!!.categoryId
                        searchTop20ByCategoy(categoryId)
                    }
                    else -> {
                        productsSearch.postValue(ProductsSearchResult(false, Constants.MESSAGE.INVALID_CATEGORY))
                    }
                }
            }catch (e: ConnectException){
                productsSearch.postValue(ProductsSearchResult(message = Constants.MESSAGE.INTERNET_CONNECTION, success = false))
            }catch(e: IOException){
                productsSearch.postValue(ProductsSearchResult(message = Constants.MESSAGE.UKNOWN_ERROR, success = false))
            }
        }
    }

    public fun searchTop20ByCategoy(categoryId: String){
        val top20Response: Response<TopTwentyByCategoryModel> = topTwentyByCategoryRepository.searchTop20ByCategory(categoryId)
        if(top20Response.code() == Constants.HTTP.SUCCESS){
            val products = top20Response.body()?.content
            var stringQuery = StringBuilder()
            products?.forEach {
                if(it.type.equals("ITEM")){
                    stringQuery.append(it.id, ",")
                }
            }
            this.getProducts(stringQuery.toString())
        }else{
            productsSearch.postValue(ProductsSearchResult(false, Constants.MESSAGE.INVALID_CATEGORY))
        }
    }


    public fun getProducts(productIds: String){
        val productResponse: Response<List<ProductDetailsModel>> = productRepository.getDetails(productIds)
        if(productResponse.code() == 200){
            productsCollection = mutableListOf()
            for(produto in productResponse.body()!!){
                productsCollection.add(produto.body!!)
            }
            productsSearch.postValue(ProductsSearchResult(true))
        }else{
            productsSearch.postValue(ProductsSearchResult(false, Constants.MESSAGE.INVALID_CATEGORY))
        }
    }
}