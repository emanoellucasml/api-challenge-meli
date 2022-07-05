package com.example.challengmelibootcamp.infraestructure
import android.content.Context
import android.content.SharedPreferences
import com.example.challengmelibootcamp.utils.Constants

class Preferences(context: Context) {
    private val mSharedPreferences: SharedPreferences = context.getSharedPreferences(Constants.APP.APP_NAME, Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        this.mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getStoredString(key: String): String {
        return this.mSharedPreferences.getString(key, "") ?: ""
    }

    fun storeBoolean(key: String, value: Boolean){
        var string: String = "false"
        if(value){
            string = "true"
        }
        this.storeString(key, string)
    }

    fun getStoreBoolean(key: String) : Boolean{
        val string: String? = this.getStoredString(key)
        if(string != null && string.isNotEmpty() && string.isNotBlank()){
            return string.toBoolean()
        }
        return false
    }
}