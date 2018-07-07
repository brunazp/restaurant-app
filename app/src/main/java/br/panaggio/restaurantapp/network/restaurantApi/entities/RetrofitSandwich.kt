package br.panaggio.restaurantapp.network.restaurantApi.entities

import com.google.gson.annotations.SerializedName

data class RetrofitSandwich(
        val id: Int,
        val name: String,
        @SerializedName("image") val photoUrl: String,
        val ingredients: List<Int>)