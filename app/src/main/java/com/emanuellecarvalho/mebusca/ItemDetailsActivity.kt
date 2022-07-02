package com.emanuellecarvalho.mebusca

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //show back button


        val product: Product? = intent.getParcelableExtra<Product>("itemProduct")
        if (product != null) {

            loadDescricao(product)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadDescricao(product: Product) {

        val service = MeliApiClient.createCategoryService()

        val call: Call<ItemProductDescriptionResponse> = service.itemDescription(product.product_id)
        call.enqueue(object : Callback<ItemProductDescriptionResponse> { //enqueue = colocar na fila
            override fun onResponse(
                call: Call<ItemProductDescriptionResponse>,
                response: Response<ItemProductDescriptionResponse>
            ) {
                val banana: ItemProductDescriptionResponse? = response.body()
                if (banana != null) {

                    product.product_description = banana.item_description
                    loadProductDetails(product)

                }
            }

            override fun onFailure(call: Call<ItemProductDescriptionResponse>, t: Throwable) {
                val s = ""
            }

        })

    }

    private fun loadProductDetails(product: Product?) {
        binding.textProductDetail.text = product?.product_name
        binding.textProductPriceDetail.text = "R$ " + product?.product_price.toString()
        val imageProductDetail: AppCompatImageView = binding.imageProductDetail;
        Picasso.get().load(product?.product_image).into(imageProductDetail)
        binding.textProductDescriptionDetail.text = product?.product_description

    }


}