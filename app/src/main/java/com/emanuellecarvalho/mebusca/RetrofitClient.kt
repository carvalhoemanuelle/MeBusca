/*
package com.emanuellecarvalho.mebusca

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class RetrofitClient {

    companion object {
        //pra não criar várias instâncias, criamos um Singleton
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        //método que retorna a instância do retrofit
        private fun getRetrofitInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if(!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build()) //faz a chamada à internet
                    .addConverterFactory(GsonConverterFactory.create()) //faz a conversão do json em classes
                    .build()
            }
            return INSTANCE
        }


        fun createPostService(): PostService {
           return getRetrofitInstance().create(PostService::class.java)
        }

        fun createCategoryService(): Any {

        }


    }

}*/
