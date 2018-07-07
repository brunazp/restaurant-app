package br.panaggio.restaurantapp.network.restaurantApi

import br.panaggio.restaurantapp.network.restaurantApi.entities.RetrofitSandwich
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApiService {
    @GET("lanche")
    fun getSandwiches(): Observable<List<RetrofitSandwich>>

    @GET("lanche/{id}")
    fun getSandwich(@Path("id") id: String): Observable<RetrofitSandwich>

    companion object {
        const val BASE_URL = "https://restaurant-app-service.herokuapp.com/api/"
    }
}