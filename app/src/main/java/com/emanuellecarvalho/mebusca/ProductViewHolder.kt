package com.emanuellecarvalho.mebusca

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var container: LinearLayout = itemView.findViewById(R.id.container)
}