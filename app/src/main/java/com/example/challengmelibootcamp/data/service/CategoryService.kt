package com.example.challengmelibootcamp.data.service

import com.example.challengmelibootcamp.data.model.CategoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryService {

    @GET("sites/MLB/domain_discovery/search?")
    fun search(
        @Query("limit") limit: Int = 1,
        @Query("q") categoryName: String
    ): Call<List<CategoryModel>>
}