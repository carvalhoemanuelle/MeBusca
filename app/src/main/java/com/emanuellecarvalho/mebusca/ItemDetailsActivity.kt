package com.emanuellecarvalho.mebusca

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.emanuellecarvalho.mebusca.api.ItemProductDescriptionResponse
import com.emanuellecarvalho.mebusca.api.MeliApiClient
import com.emanuellecarvalho.mebusca.databinding.ActivityItemDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailsBinding
    private lateinit var favoritesPreferences: FavoritesPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddFavoritesProducts.setOnClickListener {

        }

        //instanciando o SharedPreferences
        favoritesPreferences = FavoritesPreferences(this)

        val product: Product? = intent.getParcelableExtra<Product>("itemProduct")
        if (product != null) {
            loadDescription(product)
           if(!favoritesPreferences.contains(product.product_id))  {
               binding.buttonAddFavoritesProducts.setOnClickListener{
                   favoritesPreferences.add(product.product_id)
                   println("Adicionou")
               }
           } else {
               binding.buttonAddFavoritesProducts.text = "Remover dos favoritos"
               binding.buttonAddFavoritesProducts.setOnClickListener {
                   favoritesPreferences.remove(product.product_id)
               }
           }

        }

    }





    private fun loadDescription(product: Product) {

        val service = MeliApiClient.createCategoryService()

        val call: Call<ItemProductDescriptionResponse> = service.itemDescription(product.product_id)
        call.enqueue(object : Callback<ItemProductDescriptionResponse> {
            override fun onResponse(
                call: Call<ItemProductDescriptionResponse>,
                response: Response<ItemProductDescriptionResponse>
            ) {
                val dataApi: ItemProductDescriptionResponse? = response.body()
                if (dataApi != null) {

                    product.product_description = dataApi.item_description
                    loadProductDetails(product)

                }
            }

            override fun onFailure(call: Call<ItemProductDescriptionResponse>, t: Throwable) {
                // TODO: implementar aqui
                val s = ""
            }

        })

    }

    private fun saveFavorites(product: Product) {
        favoritesPreferences.add(product.product_id)
    }

    private fun loadProductDetails(product: Product?) {
        binding.textProductDetail.text = product?.product_name
        binding.textProductPriceDetail.text = "R$ " + product?.product_price.toString()
        val imageProductDetail: AppCompatImageView = binding.imageProductDetail;
        Picasso.get().load(product?.product_image).into(imageProductDetail)
        binding.textProductDescriptionDetail.text = product?.product_description

    }


}