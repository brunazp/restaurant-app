package br.panaggio.restaurantapp.features.sandwichDetails.useCases

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.domain.useCases.FetchSandwichUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class FetchSandwich(
        val restaurantApiDataSource: RestaurantApiDataSource,
        val scheduler: Scheduler) : FetchSandwichUseCase {
    override fun execute(sandwichId: Int): Observable<Sandwich> {
        return restaurantApiDataSource
                .fetchSandwich(sandwichId)
                .subscribeOn(scheduler)
    }
}