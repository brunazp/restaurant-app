package br.panaggio.restaurantapp.network.restaurantApi

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.entities.Offer
import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.network.restaurantApi.entities.RetrofitCreateOrderItemBody
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

    override fun createOrderItem(sandwichId: Int, extraIngredients: List<Ingredient>): Completable {
        val extraIds = extraIngredients
                .map { it.id }
                .joinToString(prefix = "[", postfix = "]")
        return restaurantApiService
                .createOrderItem(sandwichId, RetrofitCreateOrderItemBody(extraIds))
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
                    val observableOrderItemWithSandwich = Observable
                            .zip(observableOrderItem,
                                    observableSandwich,
                                    BiFunction<OrderItem, Sandwich, OrderItem> { orderItem, sandwichItem ->
                                        orderItem.apply { sandwich = sandwichItem }
                                    }
                            )
                    val observableExtras = fetchExtraIngredients(it.extras)
                    Observable
                            .zip(
                                    observableOrderItemWithSandwich,
                                    observableExtras,
                                    BiFunction<OrderItem, List<Ingredient>, OrderItem> { orderItem, extrasIngredients ->
                                        orderItem.apply { extras = extrasIngredients }
                                    }
                            )
                }
                .toList()
                .toObservable()
    }

    private fun fetchExtraIngredients(extrasIds: List<Int>): Observable<List<Ingredient>> {
        return fetchIngredients()
                .flatMap {
                    val serverIngredientList = it
                    val map = serverIngredientList.mapNotNull { it.id to it }.toMap()
                    val ingredientList = extrasIds.mapNotNull { map[it] }
                    Observable.just(ingredientList)
                }
    }

    override fun fetchOffers(): Observable<List<Offer>> {
        return restaurantApiService.getOffers()
    }

    override fun fetchIngredients(): Observable<List<Ingredient>> {
        return restaurantApiService
                .getIngredients()
                .flatMap { Observable.fromIterable(it) }
                .map { IngredientMapper.mapRetrofitToDomain(it) }
                .toList()
                .toObservable()
    }
}