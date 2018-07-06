package br.panaggio.restaurantapp.domain.entities

data class Sandwich(
        val id: Int,
        val name: String,
        val photoUrl: String = "",
        val ingredients: List<Ingredient> = emptyList())