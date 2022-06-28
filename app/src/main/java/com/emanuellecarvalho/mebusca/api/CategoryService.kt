package com.emanuellecarvalho.mebusca.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CategoryService {
    //TODO a cada 6h alterar o access token em todas as requisições
    @Headers("Authorization: Bearer APP_USR-3463946509863582-062714-1965091e4c73bd37c2fdabb4fcd458a7-238875989")
    @GET("sites/MLB/domain_discovery/search")
    fun list(@Query("q") categoryPartName: String): Call<List<CategoryListResponse>>




}