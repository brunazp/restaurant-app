package br.panaggio.restaurantapp.domain.useCases

import br.panaggio.restaurantapp.domain.entities.Sandwich
import io.reactivex.Observable

interface FetchSandwichesListUseCase {
    fun execute(): Observable<List<Sandwich>>
}