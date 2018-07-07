package br.panaggio.restaurantapp.domain.useCases

import br.panaggio.restaurantapp.domain.entities.Offer
import io.reactivex.Observable

interface FetchOffersListUseCase {
    fun execute() : Observable<List<Offer>>
}