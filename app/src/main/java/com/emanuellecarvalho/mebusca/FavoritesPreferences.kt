package com.emanuellecarvalho.mebusca

import android.content.Context
import android.content.SharedPreferences


class FavoritesPreferences (context: Context){

    private val ID_PRODUCT: String = "ID_PRODUCT_FAV"

    private val APP_PREF_ONE = "appPrefOne"

    private val preferences: SharedPreferences =
        context.getSharedPreferences(APP_PREF_ONE, Context.MODE_PRIVATE)


    /**
     * Check if a product ID is contained as a Favorite and return a boolean
     * @return true if productId already exists
     */
    fun contains(productId: String): Boolean {
        return preferences.contains(ID_PRODUCT)
    }

    /**
     * If a product ID is not contained as a Favorite, then the ID can be added
     */
    fun add(productId: String) {
        preferences.edit().putString(ID_PRODUCT, productId).commit()
    }

    /**
     * If a product ID is contained as a Favorite, can be removed
     */
    fun remove(productId: String) {
        preferences.edit().remove(ID_PRODUCT).commit()
    }

}
