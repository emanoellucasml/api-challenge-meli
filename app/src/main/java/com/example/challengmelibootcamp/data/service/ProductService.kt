package com.example.challengmelibootcamp.data.service

import com.example.challengmelibootcamp.data.model.ProductDescriptionModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("items?")
    fun getDetails(
        @Query("ids") ids: String,
    ): Call<List<ProductDescriptionModel>>

}

