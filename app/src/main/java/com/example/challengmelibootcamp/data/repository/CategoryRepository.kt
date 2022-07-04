package com.example.challengmelibootcamp.data.repository

import com.example.challengmelibootcamp.data.model.CategoryModel
import com.example.challengmelibootcamp.data.remote.RetrofitClient
import com.example.challengmelibootcamp.data.service.CategoryService
import retrofit2.Call
import retrofit2.Response

class CategoryRepository {

    private val categoryService: CategoryService = RetrofitClient.createService(CategoryService::class.java)



    suspend fun searchCategory(category: String): Response<List<CategoryModel>>{
        return categoryService.search(categoryName = category).execute()
    }
}