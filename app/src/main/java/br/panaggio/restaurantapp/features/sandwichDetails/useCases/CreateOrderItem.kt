package br.panaggio.restaurantapp.features.sandwichDetails.useCases

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.useCases.CreateOrderItemUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler

class CreateOrderItem(val restaurantApiDataSource: RestaurantApiDataSource,
                      val scheduler: Scheduler) : CreateOrderItemUseCase {
    override fun execute(sandwichId: Int, extraIngredients: List<Ingredient>): Completable {
        return restaurantApiDataSource
                .createOrderItem(sandwichId, extraIngredients)
                .subscribeOn(scheduler)
    }
}