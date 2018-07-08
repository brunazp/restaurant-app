package br.panaggio.restaurantapp.features.shoppingCart.useCases

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.domain.useCases.FetchOrderItemsUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class FetchOrderItems(
        val restaurantApiDataSource: RestaurantApiDataSource,
        val scheduler: Scheduler) : FetchOrderItemsUseCase {
    override fun execute(): Observable<List<OrderItem>> {
        return restaurantApiDataSource
                .fetchOrderItems()
                .subscribeOn(scheduler)
    }

}