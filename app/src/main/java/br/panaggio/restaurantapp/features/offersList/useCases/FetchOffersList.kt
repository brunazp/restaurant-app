package br.panaggio.restaurantapp.features.offersList.useCases

import br.panaggio.restaurantapp.domain.entities.Offer
import br.panaggio.restaurantapp.domain.useCases.FetchOffersListUseCase
import io.reactivex.Observable

class FetchOffersList : FetchOffersListUseCase {
    override fun execute(): Observable<List<Offer>> {
        val offers = listOf(
                Offer(10, "Name 16", "Description1"),
                Offer(21, "Name 16", "Description1"),
                Offer(31, "Name 154", "Description1"),
                Offer(41, "Name 13", "Description1"),
                Offer(51, "Name 12", "Description1"),
                Offer(61, "Name 11", "Description1"),
                Offer(71, "Name 11", "Description1"),
                Offer(81, "Name 11", "Description1"),
                Offer(91, "Name 12", "Description1"),
                Offer(11, "Name 11", "Description1")
        )
        return Observable.just(offers)
    }

}