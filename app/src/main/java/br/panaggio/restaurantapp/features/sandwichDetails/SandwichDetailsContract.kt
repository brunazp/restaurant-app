package br.panaggio.restaurantapp.features.sandwichDetails

import br.panaggio.restaurantapp.domain.entities.Sandwich

interface SandwichDetailsContract {
    interface View {
        fun displaySandwich(sandwich: Sandwich, price: Double)
        fun displayError(error: Throwable)
    }
}