package com.example.challengmelibootcamp.data.service

import com.example.challengmelibootcamp.data.model.CategoryModel
import com.example.challengmelibootcamp.data.model.Top20ByCategoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Top20ByCategoryService {
    @GET("highlights/MLB/category/{category_id}")
    fun getTop20ByCategory(
        @Path(value = "category_id", encoded = true) category_id: String
    ): Call<Top20ByCategoryModel>
}


//@GET("Task/{id}")
//fun load(@Path(value = "id", encoded = true) id: Int): Call<TaskModel>