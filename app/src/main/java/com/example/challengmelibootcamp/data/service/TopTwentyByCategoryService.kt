package com.example.challengmelibootcamp.data.service

import com.example.challengmelibootcamp.data.model.TopTwentyByCategoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TopTwentyByCategoryService {
    @GET("highlights/MLB/category/{category_id}")
    fun getTop20ByCategory(
        @Path(value = "category_id", encoded = true) category_id: String
    ): Call<TopTwentyByCategoryModel>
}

