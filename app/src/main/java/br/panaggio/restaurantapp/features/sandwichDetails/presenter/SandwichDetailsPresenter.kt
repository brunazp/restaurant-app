package br.panaggio.restaurantapp.features.sandwichDetails.presenter

import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.domain.useCases.FetchSandwichUseCase
import br.panaggio.restaurantapp.features.sandwichDetails.SandwichDetailsContract
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class SandwichDetailsPresenter(
        val view: SandwichDetailsContract.View,
        val fetchSandwichUseCase: FetchSandwichUseCase,
        val uiScheduler: Scheduler) {

    private val subscriptions: CompositeDisposable by lazy { CompositeDisposable() }
    private lateinit var sandwich: Sandwich

    fun loadSandwich(sandwichId: Int) {
        val subscription = fetchSandwichUseCase
                .execute(sandwichId)
                .observeOn(uiScheduler)
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { displaySandwich(it) },
                        { view.displayError(it) }
                )
        subscriptions.add(subscription)
    }

    fun release() {
        subscriptions.clear()
    }

    private fun displaySandwich(sandwich: Sandwich) {
        this.sandwich = sandwich
        val sandwichPrice = sandwich.ingredients.sumByDouble { it.price }
        view.displaySandwich(sandwich, sandwichPrice)
    }

    fun clickedOrder() {
        view.close()
    }
}