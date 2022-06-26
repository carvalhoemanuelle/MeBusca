package com.emanuellecarvalho.mebusca

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler


@Parcelize
data class Product(
    var product_name: String,
    var product_price: Double,
    var product_description: String,
    var product_image: String,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    companion object : Parceler<Product> {

        override fun Product.write(parcel: Parcel, flags: Int) {
            parcel.writeString(product_name)
            parcel.writeDouble(product_price)
            parcel.writeString(product_description)
            parcel.writeString(product_image)
        }

        override fun create(parcel: Parcel): Product {
            return Product(parcel)
        }
    }
}


