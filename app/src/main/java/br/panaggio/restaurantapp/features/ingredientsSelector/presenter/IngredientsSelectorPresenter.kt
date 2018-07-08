package br.panaggio.restaurantapp.features.ingredientsSelector.presenter

import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.useCases.FetchIngredientsListUseCase
import br.panaggio.restaurantapp.features.ingredientsSelector.IngredientsSelectorContract
import io.reactivex.Scheduler

class IngredientsSelectorPresenter(
        val view: IngredientsSelectorContract.View,
        val fetchIngredientsList: FetchIngredientsListUseCase,
        val uiScheduler: Scheduler) {
    fun loadIngredients() {
        fetchIngredientsList
                .execute()
                .observeOn(uiScheduler)
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { displayItems(it) },
                        { view.displayError(it) }
                )
    }

    fun release() {

    }

    private fun displayItems(ingredients: List<Ingredient>) {
        if (ingredients.isEmpty()) {
            view.displayEmpty()
        } else {
            view.displayItems(ingredients)
        }
    }
}