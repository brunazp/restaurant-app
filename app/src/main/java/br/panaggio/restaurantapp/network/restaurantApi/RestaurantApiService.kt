package br.panaggio.restaurantapp.network.restaurantApi

import br.panaggio.restaurantapp.domain.entities.Offer
import br.panaggio.restaurantapp.network.restaurantApi.entities.RetrofitIngredient
import br.panaggio.restaurantapp.network.restaurantApi.entities.RetrofitOrderItem
import br.panaggio.restaurantapp.network.restaurantApi.entities.RetrofitSandwich
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestaurantApiService {
    @GET("lanche")
    fun getSandwiches(): Observable<List<RetrofitSandwich>>

    @GET("lanche/{id}")
    fun getSandwich(@Path("id") id: Int): Observable<RetrofitSandwich>

    @GET("ingrediente/de/{id}")
    fun getSandwichIngredients(@Path("id") sandwichId: Int): Observable<List<RetrofitIngredient>>

    @GET("promocao")
    fun getOffers(): Observable<List<Offer>>

    @GET("pedido")
    fun getOrderItems(): Observable<List<RetrofitOrderItem>>

    @PUT("pedido/{id}")
    fun createOrderItem(@Path("id") sandwichId: Int): Observable<RetrofitOrderItem>

    companion object {
        const val BASE_URL = "https://restaurant-app-service.herokuapp.com/api/"
    }
}