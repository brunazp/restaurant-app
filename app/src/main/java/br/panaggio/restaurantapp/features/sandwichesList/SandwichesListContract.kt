package br.panaggio.restaurantapp.features.sandwichesList

import br.panaggio.restaurantapp.domain.entities.Sandwich

interface SandwichesListContract {
    interface View {
        fun displaySandwiches(sandwiches: List<Sandwich>)
    }
}