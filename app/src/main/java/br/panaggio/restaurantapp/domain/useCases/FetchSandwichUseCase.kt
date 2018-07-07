package br.panaggio.restaurantapp.domain.useCases

import br.panaggio.restaurantapp.domain.entities.Sandwich
import io.reactivex.Observable

interface FetchSandwichUseCase {
    fun execute(sandwichId: Int): Observable<Sandwich>
}