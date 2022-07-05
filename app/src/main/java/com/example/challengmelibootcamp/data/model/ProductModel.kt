package com.example.challengmelibootcamp.data.model

import com.google.gson.annotations.SerializedName

class ProductModel {

    @SerializedName("title")
    var title: String = ""

    @SerializedName("subtitle")
    var subtitle: String? = ""

    @SerializedName("id")
    var id: String = ""

    @SerializedName("secure_thumbnail")
    var thumbnail: String = ""

    @SerializedName("type")
    var type: String = ""

    @SerializedName("sold_quantity")
    var soldQuantity: String = ""

    @SerializedName("available_quantity")
    var availableQuantity: String = ""

    @SerializedName("price")
    var price: String = ""

    @SerializedName("pictures")
    var pictures: MutableList<ProductPictureModel> = mutableListOf()
}