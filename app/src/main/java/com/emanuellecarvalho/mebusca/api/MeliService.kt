package com.emanuellecarvalho.mebusca.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


const val ACCESS_TOKEN: String =
    "APP_USR-3463946509863582-070315-d3cc456b08deb1daca1e83c0adbe2811-238875989"


interface CategoryService {
    //TODO a cada 6h alterar o access token em todas as requisições
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("sites/MLB/domain_discovery/search?limit=1")
    fun list(@Query("q") categoryPartName: String): Call<List<CategoryPredictorResponse>>


    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("highlights/MLB/category/{category_id}")
    fun highlightsItemList(@Path("category_id") categoryId: String): Call<HighlightsProductResponse>

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("items")
    fun itemList(@Query("ids") ids: String): Call<List<ItemProductResponse>>

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("items/{product_id}/description")
    fun itemDescription(@Path("product_id") productId: String): Call<ItemProductDescriptionResponse>


}