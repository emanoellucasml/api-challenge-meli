package com.example.challengmelibootcamp.data.repository

import com.example.challengmelibootcamp.data.model.CategoryModel
import com.example.challengmelibootcamp.data.model.Top20ByCategoryModel
import com.example.challengmelibootcamp.data.remote.RetrofitClient
import com.example.challengmelibootcamp.data.service.CategoryService
import com.example.challengmelibootcamp.data.service.Top20ByCategoryService
import retrofit2.Response

class Top20ByCategoryRepository {

    private val top20ByCategoryService: Top20ByCategoryService = RetrofitClient.createService(Top20ByCategoryService::class.java)


    fun searchTop20ByCategory(categoryId: String): Response<Top20ByCategoryModel> {
        return top20ByCategoryService.getTop20ByCategory(categoryId).execute()
    }
}