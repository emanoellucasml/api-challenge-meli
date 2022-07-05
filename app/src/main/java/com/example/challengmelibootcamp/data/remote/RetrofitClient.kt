package com.example.challengmelibootcamp.data.remote

import com.example.challengmelibootcamp.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object{
        private lateinit var instance: Retrofit
        private const val BASE_URL: String = Constants.API.BASE_URL
        private const val ACCESS_TOKEN: String = Constants.API_HEADER.AUTHORIZATION_VALUE

        private fun getRetrofitInstance() : Retrofit{
            if(!Companion::instance.isInitialized){

                val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()


                httpClient.addInterceptor(object: Interceptor{
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request = chain
                            .request()
                            .newBuilder()
                            .addHeader(Constants.API.AUTHORIZATION, Constants.API_HEADER.AUTHORIZATION_VALUE)
                            .build()
                        return chain.proceed(request)
                    }
                })

                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }

            return instance
        }

        public fun <T> createService(service: Class<T>): T {
            return getRetrofitInstance().create(service)
        }
    }


}