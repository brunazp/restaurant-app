package br.panaggio.restaurantapp.domain.useCases

import io.reactivex.Completable

interface CreateOrderItemUseCase {
    fun execute(sandwichId: Int): Completable
}