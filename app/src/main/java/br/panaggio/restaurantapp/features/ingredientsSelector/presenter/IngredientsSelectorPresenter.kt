package br.panaggio.restaurantapp.features.ingredientsSelector.presenter

import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.useCases.FetchIngredientsListUseCase
import br.panaggio.restaurantapp.features.ingredientsSelector.IngredientsSelectorContract
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class IngredientsSelectorPresenter(
        val view: IngredientsSelectorContract.View,
        val fetchIngredientsList: FetchIngredientsListUseCase,
        val uiScheduler: Scheduler) {

    private val subscriptions : CompositeDisposable by lazy { CompositeDisposable() }

    fun loadIngredients() {
        val subscription = fetchIngredientsList
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

    private fun displayItems(ingredients: List<Ingredient>) {
        if (ingredients.isEmpty()) {
            view.displayEmpty()
        } else {
            view.displayItems(ingredients)
        }
    }
}