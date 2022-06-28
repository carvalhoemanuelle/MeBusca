package com.emanuellecarvalho.mebusca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuellecarvalho.mebusca.adapter.ProductAdapter
import com.emanuellecarvalho.mebusca.api.CategoryListResponse
import com.emanuellecarvalho.mebusca.api.MeliApiClient
import com.emanuellecarvalho.mebusca.databinding.ActivityProductListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerAllProducts.adapter = ProductAdapter(listaTeste())
        binding.recyclerAllProducts.layoutManager = LinearLayoutManager(this)


        //Esconde a barra de navegação
        supportActionBar?.hide()


        //Layout da recycler
        binding.recyclerAllProducts.layoutManager = LinearLayoutManager(baseContext)


        //Definir Adapter
       // binding.recyclerAllProducts.adapter = adapter


        //recebendo os dados
       // val product = intent.getParcelableArrayListExtra<Product>("PRODUCTS")
       // binding.textProductName.text = product.toString()


        //conectar o Retrofit
        //chamada assíncrona
        val service = MeliApiClient.createCategoryService()
        val call: Call<List<CategoryListResponse>> = service.list("bola")
        call.enqueue(object : Callback<List<CategoryListResponse>> { //enqueue = colocar na fila
            override fun onResponse(
                call: Call<List<CategoryListResponse>>,
                response: Response<List<CategoryListResponse>>
            ) {
                val list = response.body()
            }

            override fun onFailure(call: Call<List<CategoryListResponse>>, t: Throwable) {
                val s = ""
            }

        })

    }


}

