package com.example.challengmelibootcamp.data.model

import com.google.gson.annotations.SerializedName

class CategoryModel {
    @SerializedName("domain_id")
    var domainId: String = ""

    @SerializedName("domain_name")
    var domainName: String = ""

    @SerializedName("category_id")
    var categoryId: String = ""

    @SerializedName("category_name")
    var categoryName: String = ""
}