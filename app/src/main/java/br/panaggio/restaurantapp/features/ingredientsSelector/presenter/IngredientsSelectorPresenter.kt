package br.panaggio.restaurantapp.features.ingredientsSelector.presenter

import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.features.ingredientsSelector.IngredientsSelectorContract

class IngredientsSelectorPresenter(
        val view: IngredientsSelectorContract.View) {
    fun loadIngredients() {
        val ingredients = listOf<Ingredient>(
                Ingredient(1, "Alface1", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface2", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface3", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface4", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface5", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface6", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface7", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface8", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface9", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface10", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface11", 0.40, "https://goo.gl/U01SnT"),
                Ingredient(1, "Alface12", 0.40, "https://goo.gl/U01SnT")
        )

        displayItems(ingredients)
    }

    fun release() {

    }

    private fun displayItems(ingredients: List<Ingredient>) {
        if (ingredients.isEmpty()) {
            view.displayEmpty()
        } else {
            view.displayItems(ingredients)
        }
    }
}