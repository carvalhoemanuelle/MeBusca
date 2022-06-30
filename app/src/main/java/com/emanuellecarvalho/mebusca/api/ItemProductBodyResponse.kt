package com.emanuellecarvalho.mebusca.api

import com.google.gson.annotations.SerializedName

class ItemProductBodyResponse {

    @SerializedName("id")
    var item_id: String = ""

    @SerializedName("title")
    var item_title: String = ""

    @SerializedName("price")
    var item_price: Float = 0.0F

    @SerializedName("thumbnail")
    var item_thumbnail: String = ""


}