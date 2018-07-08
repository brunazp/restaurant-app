package br.panaggio.restaurantapp.features.sandwichDetails

import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.entities.Sandwich

interface SandwichDetailsContract {
    interface View {
        fun displaySandwich(sandwich: Sandwich, price: Double, extrasIngredient: List<Ingredient>)
        fun showLoading()
        fun hideLoading()
        fun displayError(error: Throwable)
        fun close()
        fun showCreatingOrderError(error: Throwable)
        fun openIngredientsSelector()
    }
}