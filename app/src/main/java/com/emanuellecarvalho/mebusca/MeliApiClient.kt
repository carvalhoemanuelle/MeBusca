package com.emanuellecarvalho.mebusca

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MeliApiClient {

    companion object {
        //pra não criar várias instâncias, criamos um Singleton
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://api.mercadolibre.com/"

        private fun getRetrofitInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            INSTANCE = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(http.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return INSTANCE
        }

        fun createCategoryService(): CategoryService {
            return getRetrofitInstance().create(CategoryService::class.java)
        }
    }

}