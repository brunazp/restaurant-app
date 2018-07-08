package br.panaggio.restaurantapp.features.shoppingCart

import br.panaggio.restaurantapp.domain.entities.OrderItem

interface ShoppingCartContract {
    interface View {
        fun displayOrderItems(offers: List<OrderItem>)
        fun showLoading()
        fun hideLoading()
        fun displayError(error: Throwable)
        fun displayEmpty()
    }
}