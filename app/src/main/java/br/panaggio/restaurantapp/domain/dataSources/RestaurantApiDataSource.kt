package br.panaggio.restaurantapp.domain.dataSources


import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.entities.Offer
import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.domain.entities.Sandwich
import io.reactivex.Completable
import io.reactivex.Observable

interface RestaurantApiDataSource {
    fun fetchSandwiches(): Observable<List<Sandwich>>
    fun fetchSandwich(sandwichId: Int): Observable<Sandwich>
    fun fetchOffers(): Observable<List<Offer>>
    fun createOrderItem(sandwichId: Int, extraIngredients: List<Ingredient>): Completable
    fun fetchOrderItems(): Observable<List<OrderItem>>
    fun fetchIngredients() : Observable<List<Ingredient>>
}