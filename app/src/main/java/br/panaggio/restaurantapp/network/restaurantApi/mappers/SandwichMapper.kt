package br.panaggio.restaurantapp.network.restaurantApi.mappers

import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.network.restaurantApi.entities.RetrofitSandwich

class SandwichMapper {
    companion object {
        fun mapRetrofitToDomain(retrofitSandwich: RetrofitSandwich) = Sandwich(
                id = retrofitSandwich.id,
                name = retrofitSandwich.name,
                photoUrl = retrofitSandwich.photoUrl
        )
    }
}