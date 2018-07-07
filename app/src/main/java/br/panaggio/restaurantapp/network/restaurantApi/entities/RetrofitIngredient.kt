package br.panaggio.restaurantapp.network.restaurantApi.entities

import com.google.gson.annotations.SerializedName

data class RetrofitIngredient(
        val id: Int,
        val name: String,
        val price: Double,
        @SerializedName("image") val photoUrl: String
)