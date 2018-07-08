package br.panaggio.restaurantapp.network.restaurantApi

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.entities.Offer
import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.network.restaurantApi.mappers.IngredientMapper
import br.panaggio.restaurantapp.network.restaurantApi.mappers.OrderItemMapper
import br.panaggio.restaurantapp.network.restaurantApi.mappers.SandwichMapper
import io.reactivex.Completable
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

    override fun fetchSandwich(sandwichId: Int): Observable<Sandwich> {
        val observableSandwich = restaurantApiService
                .getSandwich(sandwichId)
                .map { SandwichMapper.mapRetrofitToDomain(it) }
        val observableIngredients = fetchSandwichIngredients(sandwichId)
        return Observable
                .zip(observableSandwich,
                        observableIngredients,
                        BiFunction<Sandwich, List<Ingredient>, Sandwich> { sandwich, ingredientsList ->
                            sandwich.apply { ingredients = ingredientsList }
                        }
                )
    }

    override fun createOrderItem(sandwichId: Int): Completable {
        return restaurantApiService
                .createOrderItem(sandwichId)
                .ignoreElements()

    }

    fun fetchSandwichIngredients(sandwichId: Int): Observable<List<Ingredient>> {
        return restaurantApiService
                .getSandwichIngredients(sandwichId)
                .flatMap { Observable.fromIterable(it) }
                .map { IngredientMapper.mapRetrofitToDomain(it) }
                .toList()
                .toObservable()
    }

    override fun fetchOrderItems(): Observable<List<OrderItem>> {
        return restaurantApiService
                .getOrderItems()
                .flatMap { Observable.fromIterable(it) }
                .flatMap {
                    val observableSandwich = fetchSandwich(it.sandwichId)
                    val observableOrderItem = Observable
                            .just(it)
                            .map { OrderItemMapper.mapRetrofitToDomain(it) }

                    Observable
                            .zip(observableOrderItem,
                                    observableSandwich,
                                    BiFunction<OrderItem, Sandwich, OrderItem> { orderItem, sandwichItem ->
                                        orderItem.apply { sandwich = sandwichItem }
                                    }
                            )
                }
                .toList()
                .toObservable()
    }

    override fun fetchOffers(): Observable<List<Offer>> {
        return restaurantApiService.getOffers()
    }

    override fun fetchIngredients() : Observable<List<Ingredient>> {
        return restaurantApiService
                .getIngredients()
                .flatMap { Observable.fromIterable(it) }
                .map { IngredientMapper.mapRetrofitToDomain(it) }
                .toList()
                .toObservable()
    }
}