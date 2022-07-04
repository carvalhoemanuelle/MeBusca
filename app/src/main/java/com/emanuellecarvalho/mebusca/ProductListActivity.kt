package com.emanuellecarvalho.mebusca

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
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
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

        progressBar = findViewById(R.id.progressBar)

        //Layout da recycler
        binding.recyclerAllProducts.layoutManager = LinearLayoutManager(baseContext)


        //Evento de Enter no EditText
        binding.editSearch.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                if(validateInput(binding.editSearch.text.toString())) {
                    cleanProductList()
                    progressBar.visibility = View.VISIBLE
                    categoryPredictor(binding.editSearch.text.toString())
                    return@setOnKeyListener true
                }
                else  {
                    Toast.makeText(this, "Digite um produto válido", Toast.LENGTH_LONG).show()
                }

            }
            return@setOnKeyListener false
        }


    }

    private fun onClickItem(product: Product) {
        val intent = Intent(this, ItemDetailsActivity::class.java)
        intent.putExtra("itemProduct", product)
        startActivity(intent)
    }

    private fun messageErrorUser(str: String) {
        Toast.makeText(baseContext, str, Toast.LENGTH_LONG).show()
    }

    private fun cleanProductList() {
        binding.recyclerAllProducts.adapter = ProductAdapter(arrayListOf()) {

        }
        binding.recyclerAllProducts.layoutManager = LinearLayoutManager(this)
    }


   fun validateInput(searchValue: String): Boolean{
        if (searchValue == "") {
            return false
        }
        if(searchValue.length < 3) {
            return false
        }
        return true
    }


    private fun bestSellersByCategory(categoryId: String) {
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
                if (response.isSuccessful) {
                    val productIds = highlightsProduct?.content?.stream()?.map { it.product_id }
                        ?.collect(Collectors.toList())
                    if (productIds != null) {
                        findProducts(productIds)
                        progressBar.visibility = View.GONE
                    }
                } else {
                    messageErrorUser("Erro interno no servidor blablabla")
                    progressBar.visibility = View.GONE
                }

            }

            override fun onFailure(call: Call<HighlightsProductResponse>, t: Throwable) {
                Toast.makeText(baseContext, "Produto não encontrado", Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE

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
            }

        })
    }


    private fun loadProducts(responseAPI: List<ItemProductResponse>?) {
        //converter em lista de produtos
        val products: MutableList<Product> = ArrayList()

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


    private fun categoryPredictor(searchValue: String) {
        //conectar o Retrofit e faz chamada assíncrona
        val service = MeliApiClient.createCategoryService()

        val call: Call<List<CategoryPredictorResponse>> = service.list(searchValue)
        call.enqueue(object :
            Callback<List<CategoryPredictorResponse>> { //enqueue = colocar na fila

            override fun onResponse(
                call: Call<List<CategoryPredictorResponse>>,
                response: Response<List<CategoryPredictorResponse>>
            ) {
                if (response.isSuccessful) {
                    val categories = response.body()
                    if (categories?.isEmpty()!!) {
                        messageErrorUser("Produto não encontrado. Verifique se a palavra está escrita corretamente.")
                        progressBar.visibility = View.GONE
                    } else {
                        categories.get(0).let { bestSellersByCategory(it.category_id) }
                        progressBar.visibility = View.GONE
                    }
                } else {
                    messageErrorUser("Erro interno no servidor.")
                    progressBar.visibility = View.GONE
                }

            }

            override fun onFailure(call: Call<List<CategoryPredictorResponse>>, t: Throwable) {
                messageErrorUser("Verifique sua conexão de internet.")
                progressBar.visibility = View.GONE
            }

        })
    }


}