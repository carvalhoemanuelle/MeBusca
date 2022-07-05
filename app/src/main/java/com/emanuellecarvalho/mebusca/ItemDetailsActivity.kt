package com.emanuellecarvalho.mebusca

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
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
               addFavoriteButton(product)

            /*   binding.buttonAddFavoritesProducts.setOnClickListener{
                   favoritesPreferences.add(product.product_id)
                   println("Adicionou")
               }*/
           } else {
               removeFavoriteButton(product)
              /* binding.buttonAddFavoritesProducts.text = "Remover dos favoritos"
               binding.buttonAddFavoritesProducts.setOnClickListener {
                   favoritesPreferences.remove(product.product_id)
               }*/
           }

        }

    }

    private fun addFavoriteButton(product: Product){
        val drawableFilled = R.drawable.ic_baseline_favorite_filled

        binding.buttonAddFavoritesProducts.text = "Remover dos favoritos"
        binding.buttonAddFavoritesProducts.setCompoundDrawablesWithIntrinsicBounds(drawableFilled, 0, 0, 0)
        binding.buttonAddFavoritesProducts.setOnClickListener{
            favoritesPreferences.add(product.product_id)
            removeFavoriteButton(product)

        }

    }


    private fun removeFavoriteButton(product: Product) {
        val drawableEmpty = R.drawable.ic_baseline_favorite
        binding.buttonAddFavoritesProducts.text = "Adicionar aos favoritos"
        binding.buttonAddFavoritesProducts.setCompoundDrawablesWithIntrinsicBounds(drawableEmpty, 0, 0, 0)
        binding.buttonAddFavoritesProducts.setOnClickListener {
            favoritesPreferences.remove(product.product_id)
            addFavoriteButton(product)
        }
    }




    /**
     * Get the description product and pass to Item Details Activity
     * */
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

    private fun loadProductDetails(product: Product?) {
        binding.textProductDetail.text = product?.product_name
        binding.textProductPriceDetail.text = "R$ " + product?.product_price.toString()
        val imageProductDetail: AppCompatImageView = binding.imageProductDetail
        Picasso.get().load(product?.product_image).into(imageProductDetail)
        binding.textProductDescriptionDetail.text = product?.product_description

    }


}