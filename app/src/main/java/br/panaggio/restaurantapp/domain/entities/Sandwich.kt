package br.panaggio.restaurantapp.domain.entities

data class Sandwich(
        val id: Int,
        val name: String,
        val photoUrl: String = "",
        var ingredients: List<Ingredient> = emptyList())