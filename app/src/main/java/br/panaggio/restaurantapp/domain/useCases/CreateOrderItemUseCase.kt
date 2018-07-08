package br.panaggio.restaurantapp.domain.useCases

import br.panaggio.restaurantapp.domain.entities.Ingredient
import io.reactivex.Completable

interface CreateOrderItemUseCase {
    fun execute(sandwichId: Int, extraIngredients: List<Ingredient>): Completable
}