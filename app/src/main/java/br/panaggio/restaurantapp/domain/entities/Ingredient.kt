package br.panaggio.restaurantapp.domain.entities

data class Ingredient(
        val id: Int,
        val name: String,
        val price: Double,
        val photoUrl: String = "")