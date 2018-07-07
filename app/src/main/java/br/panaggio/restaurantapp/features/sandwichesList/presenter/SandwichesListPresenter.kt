package br.panaggio.restaurantapp.features.sandwichesList.presenter

import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.domain.useCases.FetchSandwichesListUseCase
import br.panaggio.restaurantapp.features.sandwichesList.SandwichesListContract
import br.panaggio.restaurantapp.features.sandwichesList.useCases.FetchSandwichesList
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class SandwichesListPresenter(
        val view: SandwichesListContract.View,
        val fetchSandwichesListUseCase: FetchSandwichesListUseCase,
        val uiScheduler: Scheduler) {

    private val subscriptions : CompositeDisposable by lazy { CompositeDisposable() }

    fun loadSandwiches() {
        val subscription = fetchSandwichesListUseCase
                .execute()
                .observeOn(uiScheduler)
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { displayItems(it) },
                        { view.displayError(it) }
                )
        subscriptions.add(subscription)
    }

    fun release() {
        subscriptions.clear()
    }

    private fun displayItems(sandwiches: List<Sandwich>) {
        if (sandwiches.isEmpty()) {
            view.displayEmpty()
        } else {
            view.displaySandwiches(sandwiches)
        }
    }

}