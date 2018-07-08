package br.panaggio.restaurantapp.features.ingredientsSelector

import br.panaggio.restaurantapp.domain.entities.Ingredient

interface IngredientsSelectorContract {
    interface View {
        fun displayItems(ingredientsQuantity: Map<Ingredient, Int>)
        fun showLoading()
        fun hideLoading()
        fun displayError(error: Throwable)
        fun displayEmpty()
    }
}