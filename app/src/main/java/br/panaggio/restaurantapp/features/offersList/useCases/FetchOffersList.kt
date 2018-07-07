package br.panaggio.restaurantapp.features.offersList.useCases

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.Offer
import br.panaggio.restaurantapp.domain.useCases.FetchOffersListUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class FetchOffersList(
        val restaurantApiDataSource: RestaurantApiDataSource,
        val scheduler: Scheduler) : FetchOffersListUseCase {
    override fun execute(): Observable<List<Offer>> {
        return restaurantApiDataSource
                .fetchOffers()
                .subscribeOn(scheduler)
    }

}