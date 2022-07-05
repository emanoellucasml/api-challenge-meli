package com.example.challengmelibootcamp.data.repository

import com.example.challengmelibootcamp.data.model.ProductDescriptionModel
import com.example.challengmelibootcamp.data.remote.RetrofitClient
import com.example.challengmelibootcamp.data.service.ProductService
import retrofit2.Response

class ProductRepository {
    private val productService: ProductService = RetrofitClient.createService(ProductService::class.java)

    fun getDetails(ids: String): Response<List<ProductDescriptionModel>> {
        return productService.getDetails(ids).execute()
    }
}