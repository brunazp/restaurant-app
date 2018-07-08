package br.panaggio.restaurantapp.domain.entities

data class OrderItem(
        val id: Int,
        val sandwich: Sandwich,
        val extras: List<Ingredient>,
        val timestamp: Long)