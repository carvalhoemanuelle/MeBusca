package com.emanuellecarvalho.mebusca

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuellecarvalho.mebusca.adapter.ProductAdapter
import com.emanuellecarvalho.mebusca.api.*
import com.emanuellecarvalho.mebusca.databinding.ActivityProductListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.Collectors

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Esconde a barra de navegação
        supportActionBar?.hide()


        //Layout da recycler
        binding.recyclerAllProducts.layoutManager = LinearLayoutManager(baseContext)


        //Evento de Enter no EditText
        binding.editSearch.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                categoryPredictor(binding.editSearch.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }


    }

    fun onClickItem(product: Product) {
        // TODO: remover println
        println("Clicou")
        val intent = Intent(this, ItemDetailsActivity::class.java)
        intent.putExtra("itemProduct", product)
        startActivity(intent)
    }


    fun bestSellersByCategory(categoryId: String) {
        val service = MeliApiClient.createCategoryService()

        val callBestSeller: Call<HighlightsProductResponse> =
            service.highlightsItemList(categoryId)

        callBestSeller.enqueue(object :
            Callback<HighlightsProductResponse> { //enqueue = colocar na fila
            override fun onResponse(
                call: Call<HighlightsProductResponse>,
                response: Response<HighlightsProductResponse>
            ) {
                val highlightsProduct = response.body()
                if (highlightsProduct?.content != null) {
                    val productIds = highlightsProduct.content.stream().map { it.product_id }
                        .collect(Collectors.toList())
                    findProducts(productIds)
                }

            }

            override fun onFailure(call: Call<HighlightsProductResponse>, t: Throwable) {
                val s = ""
            }

        })

    }

    private fun findProducts(productIds: List<String>) {

        val service = MeliApiClient.createCategoryService()
        val ids = productIds.joinToString(",")

        val call: Call<List<ItemProductResponse>> = service.itemList(ids)
        call.enqueue(object : Callback<List<ItemProductResponse>> { //enqueue = colocar na fila
            override fun onResponse(
                call: Call<List<ItemProductResponse>>,
                response: Response<List<ItemProductResponse>>
            ) {
                val productsAPI: List<ItemProductResponse>? = response.body()
                loadProducts(productsAPI)
            }

            override fun onFailure(call: Call<List<ItemProductResponse>>, t: Throwable) {
                val s = ""
            }

        })
    }


    fun loadProducts(responseAPI: List<ItemProductResponse>?) {
        //converter em lista de produtos
        val products: MutableList<Product> = ArrayList();

        if (responseAPI != null) {
            //iterando a lista de resultados a partir da resposta da API
            for (productAPI in responseAPI) {
                val product = Product(
                    productAPI.item.item_id,
                    productAPI.item.item_title,
                    productAPI.item.item_price, productAPI.item.item_thumbnail, ""
                )
                products.add(product)
            }
            binding.recyclerAllProducts.adapter = ProductAdapter(products) { product ->
                onClickItem(
                    product
                )
            }
            binding.recyclerAllProducts.layoutManager = LinearLayoutManager(this)

        }
    }

    fun categoryPredictor(searchValue: String) {
        //conectar o Retrofit e faz chamada assíncrona
        val service = MeliApiClient.createCategoryService()

        val call: Call<List<CategoryPredictorResponse>> = service.list(searchValue)
        call.enqueue(object :
            Callback<List<CategoryPredictorResponse>> { //enqueue = colocar na fila

            override fun onResponse(
                call: Call<List<CategoryPredictorResponse>>,
                response: Response<List<CategoryPredictorResponse>>
            ) {
                // TODO: ver nullsafety
                val categories = response.body()
                categories?.get(0)?.let { bestSellersByCategory(it.category_id) }
            }

            override fun onFailure(call: Call<List<CategoryPredictorResponse>>, t: Throwable) {
                val s = ""
            }

        })
    }


}