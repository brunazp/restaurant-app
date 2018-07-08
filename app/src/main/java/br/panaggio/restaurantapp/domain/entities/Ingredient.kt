package br.panaggio.restaurantapp.domain.entities

import java.io.Serializable

data class Ingredient(
        val id: Int,
        val name: String,
        val price: Double,
        val photoUrl: String = "") : Serializable