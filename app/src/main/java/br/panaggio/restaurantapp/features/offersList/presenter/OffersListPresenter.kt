package br.panaggio.restaurantapp.features.offersList.presenter

import br.panaggio.restaurantapp.domain.entities.Offer
import br.panaggio.restaurantapp.domain.useCases.FetchOffersListUseCase
import br.panaggio.restaurantapp.features.offersList.OffersListContract
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class OffersListPresenter(
        val view: OffersListContract.View,
        val fetchOffersListUseCase: FetchOffersListUseCase,
        val uiScheduler: Scheduler) {
    private val subscriptions : CompositeDisposable by lazy { CompositeDisposable() }

    fun loadOffers() {
        val subscription = fetchOffersListUseCase
                .execute()
                .observeOn(uiScheduler)
                .doOnSubscribe{view.showLoading()}
                .doOnTerminate{view.hideLoading()}
                .subscribe(
                        { displayItems(it) },
                        { view.displayError(it) }
                )
        subscriptions.add(subscription)
    }

    fun release() {
        subscriptions.clear()
    }

    private fun displayItems(offers: List<Offer>) {
        if (offers.isEmpty()) {
            view.displayEmpty()
        } else {
            view.displayOffers(offers)
        }
    }
}