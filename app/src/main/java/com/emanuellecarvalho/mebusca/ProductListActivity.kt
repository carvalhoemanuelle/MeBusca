package com.emanuellecarvalho.mebusca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emanuellecarvalho.mebusca.databinding.ActivityProductListBinding

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //recebendo os dados
        val product = intent.getParcelableArrayListExtra<Product>("PRODUCTS")
        binding.textProductName.text = product.toString()


    }

}