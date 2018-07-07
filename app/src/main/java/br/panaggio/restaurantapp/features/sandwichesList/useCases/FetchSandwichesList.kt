package br.panaggio.restaurantapp.features.sandwichesList.useCases

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.domain.useCases.FetchSandwichesListUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class FetchSandwichesList(
        val restaurantApiDataSource: RestaurantApiDataSource,
        val scheduler: Scheduler) : FetchSandwichesListUseCase {
    override fun execute(): Observable<List<Sandwich>> {
        return restaurantApiDataSource
                .fetchSandwiches()
                .subscribeOn(scheduler)
    }
}