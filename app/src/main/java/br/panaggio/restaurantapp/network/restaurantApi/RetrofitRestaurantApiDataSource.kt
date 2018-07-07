package br.panaggio.restaurantapp.network.restaurantApi

import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.network.restaurantApi.mappers.SandwichMapper
import io.reactivex.Observable

class RetrofitRestaurantApiDataSource(
        val restaurantApiService: RestaurantApiService) : RestaurantApiDataSource {
    override fun fetchSandwiches(): Observable<List<Sandwich>> {

        return restaurantApiService.getSandwiches()
                .flatMap { Observable.fromIterable(it) }
                .map { SandwichMapper.mapRetrofitToDomain(it) }
                .toList()
                .toObservable()
    }

}