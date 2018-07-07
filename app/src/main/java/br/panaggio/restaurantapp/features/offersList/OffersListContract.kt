package br.panaggio.restaurantapp.features.offersList

import br.panaggio.restaurantapp.domain.entities.Offer

interface OffersListContract {
    interface View {
        fun displayOffers(offers: List<Offer>)
        fun showLoading()
        fun hideLoading()
        fun displayError(error: Throwable)
        fun displayEmpty()
    }
}
