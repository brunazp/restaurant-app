package br.panaggio.restaurantapp.domain.entities

data class OrderItem(
        val id: Int,
        var sandwich: Sandwich? = null,
        var extras: List<Ingredient> = emptyList(),
        val timestamp: Long) {
}