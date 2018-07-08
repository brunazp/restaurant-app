package br.panaggio.restaurantapp.domain.useCases

import br.panaggio.restaurantapp.domain.entities.OrderItem
import io.reactivex.Observable

interface FetchOrderItemsUseCase {
    fun execute(): Observable<List<OrderItem>>
}