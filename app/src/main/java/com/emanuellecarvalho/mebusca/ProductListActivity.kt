package com.emanuellecarvalho.mebusca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuellecarvalho.mebusca.adapter.ProductAdapter
import com.emanuellecarvalho.mebusca.databinding.ActivityProductListBinding

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding

    //private val adapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        //Layout da recycler
        binding.recyclerAllProducts.layoutManager = LinearLayoutManager(baseContext)


        //Definir Adapter
       // binding.recyclerAllProducts.adapter = adapter


        //recebendo os dados
        val product = intent.getParcelableArrayListExtra<Product>("PRODUCTS")
       // binding.textProductName.text = product.toString()

    }

}