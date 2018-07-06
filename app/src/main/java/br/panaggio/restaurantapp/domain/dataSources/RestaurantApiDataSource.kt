package br.panaggio.restaurantapp.domain.dataSources


import br.panaggio.restaurantapp.domain.entities.Sandwich
import io.reactivex.Observable

interface RestaurantApiDataSource {
    fun fetchSandwiches() : Observable<List<Sandwich>>
}