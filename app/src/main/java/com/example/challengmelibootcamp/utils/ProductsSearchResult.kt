package com.example.challengmelibootcamp.utils

class ProductsSearchResult() {

    constructor(success: Boolean, message: String) : this() {
        this.pair = Pair<Boolean, String>(success, message)
    }

    private var pair: Pair<Boolean, String>? = null

    public fun success() : Boolean{
        return pair!!.first
    }

    public fun message() : String{
        return pair!!.second
    }
}