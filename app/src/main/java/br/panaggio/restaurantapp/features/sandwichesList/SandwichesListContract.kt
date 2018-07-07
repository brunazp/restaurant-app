package br.panaggio.restaurantapp.features.sandwichesList

import br.panaggio.restaurantapp.domain.entities.Sandwich

interface SandwichesListContract {
    interface View {
        fun displaySandwiches(sandwiches: List<Sandwich>)
        fun showLoading()
        fun hideLoading()
        fun displayError(error: Throwable)
        fun displayEmpty()
        fun openSandwichDetails(sandwich: Sandwich)
    }
}