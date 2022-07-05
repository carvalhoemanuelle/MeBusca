package com.emanuellecarvalho.mebusca.api

import com.google.gson.annotations.SerializedName

class ItemProductResponse {

    //Get details by item
    @SerializedName("body")
    lateinit var item: ItemProductBodyResponse


}