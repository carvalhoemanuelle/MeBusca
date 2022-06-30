package com.emanuellecarvalho.mebusca.api

import com.google.gson.annotations.SerializedName

class ItemProductResponse {

    @SerializedName("body")
    lateinit var item: ItemProductBodyResponse


}