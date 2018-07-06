package br.panaggio.restaurantapp.features.sandwichesList.presenter

import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.domain.useCases.FetchSandwichesListUseCase
import br.panaggio.restaurantapp.features.sandwichesList.SandwichesListContract
import br.panaggio.restaurantapp.features.sandwichesList.useCases.FetchSandwichesList

class SandwichesListPresenter(
        val view: SandwichesListContract.View,
        val fetchSandwichesListUseCase: FetchSandwichesListUseCase = FetchSandwichesList()) {
    fun loadSandwiches() {
        fetchSandwichesListUseCase.execute()
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { displayItems(it) },
                        { view.displayError(it) }
                )
    }

    private fun displayItems(sandwiches: List<Sandwich>) {
        if (sandwiches.isEmpty()) {
            view.displayEmpty()
        } else {
            view.displaySandwiches(sandwiches)
        }
    }

}