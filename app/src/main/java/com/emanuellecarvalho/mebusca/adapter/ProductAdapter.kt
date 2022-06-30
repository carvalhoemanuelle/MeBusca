package com.emanuellecarvalho.mebusca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emanuellecarvalho.mebusca.Product
import com.emanuellecarvalho.mebusca.R

class ProductAdapter(private val products: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    //cria as visualizações da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val list = LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent, false)
        return ProductViewHolder(list)
    }

    //exibe as visualizações pro usuário
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])

    }

    override fun getItemCount(): Int {
        return products.size
    }

     class ProductViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
         fun bind(product: Product) {
             val txtName = itemView.findViewById<TextView>(R.id.text_product_list);
             val txtPrice = itemView.findViewById<TextView>(R.id.text_price_product);
             txtName.text = product.product_name
             txtPrice.text = product.product_price.toString()


         }
    }
}