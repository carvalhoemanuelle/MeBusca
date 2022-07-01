package com.emanuellecarvalho.mebusca.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

data class meliAccessToken (
    var ACCESS_TOKEN:String = "APP_USR-3463946509863582-070107-69bfdf7a21f8e3f7df04ec50e2a0134c-238875989"
        )

interface CategoryService {
    //TODO a cada 6h alterar o access token em todas as requisições
    @Headers("Authorization: Bearer APP_USR-3463946509863582-070107-69bfdf7a21f8e3f7df04ec50e2a0134c-238875989")
    @GET("sites/MLB/domain_discovery/search?limit=1")
    fun list(@Query("q") categoryPartName: String): Call<List<CategoryPredictorResponse>>


    @Headers("Authorization: Bearer APP_USR-3463946509863582-070107-69bfdf7a21f8e3f7df04ec50e2a0134c-238875989")
    @GET("highlights/MLB/category/{category_id}")
    fun highlightsItemList(@Path("category_id") categoryId: String): Call<HighlightsProductResponse>

    @Headers("Authorization: Bearer APP_USR-3463946509863582-070107-69bfdf7a21f8e3f7df04ec50e2a0134c-238875989")
    @GET("items")
    fun itemList(@Query("ids") ids: String): Call<List<ItemProductResponse>>

    @Headers("Authorization: Bearer APP_USR-3463946509863582-070107-69bfdf7a21f8e3f7df04ec50e2a0134c-238875989")
    @GET("items/{category_id}/description")
    fun itemDescription(@Path("category_id") description: String): Call<ItemProductDescriptionResponse>


}