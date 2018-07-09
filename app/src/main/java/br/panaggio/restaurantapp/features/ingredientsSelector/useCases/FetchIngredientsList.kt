package br.panaggio.restaurantapp.features.ingredientsSelector.useCases

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.useCases.FetchIngredientsListUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class FetchIngredientsList(
        val restaurantApiDataSource: RestaurantApiDataSource,
        val scheduler: Scheduler) : FetchIngredientsListUseCase {

    override fun execute(): Observable<List<Ingredient>> {
        return restaurantApiDataSource
                .fetchIngredients()
                .subscribeOn(scheduler)
    }
}