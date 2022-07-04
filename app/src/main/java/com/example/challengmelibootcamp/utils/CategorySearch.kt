package com.example.challengmelibootcamp.utils

class CategorySearch(message: String, success: Boolean) {
    private val pair: Pair<Boolean, String> = Pair(success, message)

    public fun success() : Boolean{
        return pair.first
    }

    public fun message() : String{
        return pair.second
    }
}