package com.emanuellecarvalho.mebusca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emanuellecarvalho.mebusca.Product
import com.emanuellecarvalho.mebusca.R
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val products: MutableList<Product>,
    private val onClickItem: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    //cria as visualizações da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val list = LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent, false)
        return ProductViewHolder(list, onClickItem)
    }

    //exibe as visualizações pro usuário
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ProductViewHolder (itemView: View, private val onClickItem: (Product) -> Unit) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener{
                onClickItem(products[absoluteAdapterPosition])
            }
        }

        fun bind(product: Product) {
            val txtName: TextView = itemView.findViewById(R.id.text_product_list)
            val txtPrice: TextView = itemView.findViewById(R.id.text_price_product)
            val imgProduct: ImageView = itemView.findViewById(R.id.image_product)
            txtName.text = product.product_name
            txtPrice.text = "R$ " + product.product_price.toString()
            Picasso.get().load(product.product_image).into(imgProduct)

        }
    }
}