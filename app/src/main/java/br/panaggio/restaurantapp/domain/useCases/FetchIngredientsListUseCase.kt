package br.panaggio.restaurantapp.domain.useCases

import br.panaggio.restaurantapp.domain.entities.Ingredient
import io.reactivex.Observable

interface FetchIngredientsListUseCase {
    fun execute(): Observable<List<Ingredient>>
}