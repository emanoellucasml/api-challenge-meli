package com.example.challengmelibootcamp.data.model

import com.google.gson.annotations.SerializedName

class Top20ByCategoryModel {
    @SerializedName("content")
    var content: List<ProductModel> = mutableListOf()
}