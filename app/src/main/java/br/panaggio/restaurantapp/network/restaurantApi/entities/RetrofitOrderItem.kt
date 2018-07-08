package br.panaggio.restaurantapp.network.restaurantApi.entities

import com.google.gson.annotations.SerializedName

data class RetrofitOrderItem(
        val id: Int,
        @SerializedName("id_sandwich") val sandwichId: Int,
        val extras: List<Int>,
        @SerializedName("date") val timestamp: Long)