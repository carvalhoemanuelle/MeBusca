package com.emanuellecarvalho.mebusca.api

import com.google.gson.annotations.SerializedName

class CategoryPredictorResponse {

    @SerializedName("domain_id")
    var domain_id: String = ""

    @SerializedName("category_id")
    var category_id: String = ""

    @SerializedName("category_name")
    var category_name: String = ""


}