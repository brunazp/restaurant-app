package br.panaggio.restaurantapp.network.restaurantApi.mappers

import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.network.restaurantApi.entities.RetrofitOrderItem

class OrderItemMapper {
    companion object {
        fun mapRetrofitToDomain(retrofitOrderItem: RetrofitOrderItem) = OrderItem(
                id = retrofitOrderItem.id,
                timestamp = retrofitOrderItem.timestamp
        )
    }
}