package com.emanuellecarvalho.mebusca.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuellecarvalho.mebusca.ProductViewHolder
import com.emanuellecarvalho.mebusca.R

class ProductAdapter(private val context: Context) : RecyclerView.Adapter<ProductViewHolder>() {


    //cria as visualizações da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val list = LayoutInflater.from(context).inflate(R.layout.row_product, parent, false)
        val holder = ProductViewHolder(list)
        return holder
    }

    //exibe as visualizações pro usuário
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        TODO("Not yet implemented")

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}