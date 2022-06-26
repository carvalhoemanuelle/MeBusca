package com.emanuellecarvalho.mebusca

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {

    @GET("sites/MLB/domain_discovery/search")
    fun list(@Query("q") categoryPartName: String): Call<List<CategoryListResponse>>




}