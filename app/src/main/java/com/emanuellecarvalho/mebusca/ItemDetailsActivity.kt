package com.emanuellecarvalho.mebusca

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.TaskStackBuilder
import com.emanuellecarvalho.mebusca.api.ItemProductDescriptionResponse
import com.emanuellecarvalho.mebusca.api.MeliApiClient
import com.emanuellecarvalho.mebusca.databinding.ActivityItemDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddFavoritesProducts.setOnClickListener {
            saveFavorites()
        }


        val product: Product? = intent.getParcelableExtra<Product>("itemProduct")
        if (product != null) {
            loadDescription(product)
        }

        val preferencesOne = getSharedPreferences("CAIXA_UM", Context.MODE_PRIVATE)
        preferencesOne.edit().putString("ID_PRODUTO", "10").commit()

        val productIdPreference: String? = preferencesOne.getString("ID_PRODUTO", "Não encontrado")
        if (productIdPreference != null) {
            Log.d("MANU", productIdPreference)
        }

    }

    private fun loadDescription(product: Product) {

        val service = MeliApiClient.createCategoryService()

        val call: Call<ItemProductDescriptionResponse> = service.itemDescription(product.product_id)
        call.enqueue(object : Callback<ItemProductDescriptionResponse> { //enqueue = colocar na fila
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

    private fun saveFavorites() {

    }

    private fun loadProductDetails(product: Product?) {
        binding.textProductDetail.text = product?.product_name
        binding.textProductPriceDetail.text = "R$ " + product?.product_price.toString()
        val imageProductDetail: AppCompatImageView = binding.imageProductDetail;
        Picasso.get().load(product?.product_image).into(imageProductDetail)
        binding.textProductDescriptionDetail.text = product?.product_description

    }


}