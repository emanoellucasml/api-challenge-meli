package com.example.challengmelibootcamp.data.service

import com.example.challengmelibootcamp.data.model.ProductDescriptionModel
import com.example.challengmelibootcamp.data.model.ProductModelWrapper
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("items?")
    fun getDetails(
        @Query("ids") ids: String,
    ): Call<List<ProductModelWrapper>>

    @GET("items/{id}/description")
    fun getDescription(
        @Path(value = "id", encoded = true) id: String
    ): Call<ProductDescriptionModel>

}

