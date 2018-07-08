package br.panaggio.restaurantapp.domain.entities

data class OrderItem(
        val id: Int,
        var sandwich: Sandwich? = null,
        val extras: List<Ingredient> = emptyList(),
        val timestamp: Long) {
}