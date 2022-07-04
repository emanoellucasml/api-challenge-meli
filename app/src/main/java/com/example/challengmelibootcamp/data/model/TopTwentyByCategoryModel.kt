package com.example.challengmelibootcamp.data.model

import com.google.gson.annotations.SerializedName

class TopTwentyByCategoryModel {
    @SerializedName("content")
    var content: List<ProductModel> = mutableListOf()
}