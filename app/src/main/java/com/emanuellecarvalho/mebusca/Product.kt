package com.emanuellecarvalho.mebusca

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler


@Parcelize
data class Product(
    var product_name: String,
    var product_price: Float,
    var product_description: String,
    var product_image: String,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readFloat(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    companion object : Parceler<Product> {

        override fun Product.write(parcel: Parcel, flags: Int) {
            parcel.writeString(product_name)
            parcel.writeFloat(product_price)
            parcel.writeString(product_description)
            parcel.writeString(product_image)
        }

        override fun create(parcel: Parcel): Product {
            return Product(parcel)
        }
    }
}

/*
fun listaTeste(): MutableList<Product> {
    val bola = Product(
        "Bola de futebol",
        2.4,
        "Uma bola de futebola amarela e branca",
        "https://img.freepik.com/vetores-gratis/imagem-preta-branca-realistica-da-bola-de-futebol_1284-8506.jpg?w=2000"
    )

    val bolaFutebol = Product(
        "Bola de tênis",
        4.8,
        "Seis bolas de tênis na cor verde-limão",
        "https://img.freepik.com/fotos-gratis/seis-bolas-de-tenis-em-simetria_23-2147661821.jpg?w=2000"
    )

    val bolaBasquete = Product(
        "Bola de basquete",
        8.7,
        "Uma bola de basquete laranja com listas pretas",
        "https://img.freepik.com/psd-gratuitas/maquete-de-bola-de-basquete_35913-2166.jpg?w=2000"
    )

    val bolaVolei = Product(
        "Bola de vôlei",
        10.9,
        "Uma bola de vôlei nas cores amarela, branca e azul. Edição limitada",
        "https://img.freepik.com/fotos-gratis/posicao-plana-do-volei-na-areia-da-praia_23-2148662653.jpg?w=2000"
    )
    val bolaBasqueteB = Product(
        "Bola de basquete",
        8.7,
        "Uma bola de basquete laranja com listas pretas",
        "https://img.freepik.com/psd-gratuitas/maquete-de-bola-de-basquete_35913-2166.jpg?w=2000"
    )

    val bolaVoleiC = Product(
        "Bola de vôlei",
        10.9,
        "Uma bola de vôlei nas cores amarela, branca e azul. Edição limitada",
        "https://img.freepik.com/fotos-gratis/posicao-plana-do-volei-na-areia-da-praia_23-2148662653.jpg?w=2000"
    )

    return mutableListOf(bola, bolaFutebol, bolaBasquete, bolaVolei, bolaBasqueteB, bolaVoleiC)
}

*/




