package br.panaggio.restaurantapp.network.restaurantApi

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.network.restaurantApi.mappers.IngredientMapper
import br.panaggio.restaurantapp.network.restaurantApi.mappers.SandwichMapper
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class RetrofitRestaurantApiDataSource(
        val restaurantApiService: RestaurantApiService) : RestaurantApiDataSource {
    override fun fetchSandwiches(): Observable<List<Sandwich>> {

        return restaurantApiService.getSandwiches()
                .flatMap { Observable.fromIterable(it) }
                .flatMap {
                    val observableSandwich = Observable
                            .just(it)
                            .map { SandwichMapper.mapRetrofitToDomain(it) }
                    val observableIngredients = fetchSandwichIngredients(it.id)
                    Observable
                            .zip(observableSandwich,
                                    observableIngredients,
                                    BiFunction<Sandwich, List<Ingredient>, Sandwich> { sandwich, ingredientsList ->
                                        sandwich.apply { ingredients = ingredientsList }
                                    }
                            )
                }
                .toList()
                .toObservable()
    }

    fun fetchSandwichIngredients(sandwichId: Int): Observable<List<Ingredient>> {
        return restaurantApiService
                .getSandwichIngredients(sandwichId)
                .flatMap { Observable.fromIterable(it) }
                .map { IngredientMapper.mapRetrofitToDomain(it) }
                .toList()
                .toObservable()
    }

}