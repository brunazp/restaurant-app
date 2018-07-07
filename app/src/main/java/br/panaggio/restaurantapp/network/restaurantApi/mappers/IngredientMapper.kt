package br.panaggio.restaurantapp.network.restaurantApi.mappers

import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.network.restaurantApi.entities.RetrofitIngredient

class IngredientMapper {
    companion object {
        fun mapRetrofitToDomain(retrofitIngredient: RetrofitIngredient) = Ingredient(
                id = retrofitIngredient.id,
                name = retrofitIngredient.name,
                price = retrofitIngredient.price,
                photoUrl = retrofitIngredient.photoUrl
        )
    }
}