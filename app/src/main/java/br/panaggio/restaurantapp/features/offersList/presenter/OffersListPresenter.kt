package br.panaggio.restaurantapp.features.offersList.presenter

import br.panaggio.restaurantapp.domain.entities.Offer
import br.panaggio.restaurantapp.features.offersList.OffersListContract

class OffersListPresenter(
        val view: OffersListContract.View
) {
    fun loadOffers() {
        val offers = listOf(
                Offer(10, "Name 16", "Description1"),
                Offer(21, "Name 16", "Description1"),
                Offer(31, "Name 154", "Description1"),
                Offer(41, "Name 13", "Description1"),
                Offer(51, "Name 12", "Description1"),
                Offer(61, "Name 11", "Description1"),
                Offer(71, "Name 11", "Description1"),
                Offer(81, "Name 11", "Description1"),
                Offer(91, "Name 12", "Description1"),
                Offer(11, "Name 11", "Description1")
        )
        displayItems(offers)
    }

    private fun displayItems(offers: List<Offer>) {
        if (offers.isEmpty()) {
            view.displayEmpty()
        } else {
            view.displayOffers(offers)
        }
    }
}