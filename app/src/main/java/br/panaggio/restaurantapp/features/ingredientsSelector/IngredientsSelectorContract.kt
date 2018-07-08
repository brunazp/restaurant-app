package br.panaggio.restaurantapp.features.ingredientsSelector

import android.content.Intent
import br.panaggio.restaurantapp.domain.entities.Ingredient

interface IngredientsSelectorContract {
    interface View {
        fun displayItems(ingredientsQuantity: Map<Ingredient, Int>)
        fun showLoading()
        fun hideLoading()
        fun displayError(error: Throwable)
        fun displayEmpty()
        fun returnDataAndClose(result: Int, returnIntent: Intent)
    }
}