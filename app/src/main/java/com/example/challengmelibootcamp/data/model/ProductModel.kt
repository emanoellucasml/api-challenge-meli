package com.example.challengmelibootcamp.data.model

import com.google.gson.annotations.SerializedName

class ProductModel {
    @SerializedName("id")
    var id: String = ""

    @SerializedName("type")
    var type: String = ""
}