package com.example.challengmelibootcamp.data.repository

import com.example.challengmelibootcamp.data.model.TopTwentyByCategoryModel
import com.example.challengmelibootcamp.data.remote.RetrofitClient
import com.example.challengmelibootcamp.data.service.TopTwentyByCategoryService
import retrofit2.Response

class TopTwentyByCategoryRepository {

    private val top20ByCategoryService: TopTwentyByCategoryService = RetrofitClient.createService(TopTwentyByCategoryService::class.java)


    fun searchTop20ByCategory(categoryId: String): Response<TopTwentyByCategoryModel> {
        return top20ByCategoryService.getTop20ByCategory(categoryId).execute()
    }
}