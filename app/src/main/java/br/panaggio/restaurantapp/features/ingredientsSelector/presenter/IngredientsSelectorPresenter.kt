package br.panaggio.restaurantapp.features.ingredientsSelector.presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.useCases.FetchIngredientsListUseCase
import br.panaggio.restaurantapp.features.ingredientsSelector.IngredientsSelectorContract
import br.panaggio.restaurantapp.features.ingredientsSelector.view.IngredientsSelectorActivity
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class IngredientsSelectorPresenter(
        val view: IngredientsSelectorContract.View,
        val fetchIngredientsList: FetchIngredientsListUseCase,
        val uiScheduler: Scheduler) {

    private val subscriptions : CompositeDisposable by lazy { CompositeDisposable() }
    private var mapIngredientQuantity = HashMap<Ingredient, Int>()
    private val MAX_ITEMS = 10
    private val MIN_ITEMS = 0

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
            ingredients.forEach { mapIngredientQuantity.put(it, 0) }
            view.displayItems(mapIngredientQuantity)
        }
    }

    fun clickedOnIncreaseIngredient(ingredient: Ingredient) {
        val oldQuantity = mapIngredientQuantity[ingredient] ?: 0
        val newQuantity = oldQuantity + 1
        if (newQuantity <= MAX_ITEMS) {
            mapIngredientQuantity.set(ingredient, newQuantity)
            view.displayItems(mapIngredientQuantity)
        }
    }

    fun clickedOnDecreaseIngredient(ingredient: Ingredient) {
        val oldQuantity = mapIngredientQuantity[ingredient] ?: 0
        val newQuantity = oldQuantity - 1
        if (newQuantity >= MIN_ITEMS) {
            mapIngredientQuantity.set(ingredient, newQuantity)
            view.displayItems(mapIngredientQuantity)
        }
    }

    fun clickedOnCompleteSelection() {
        val bundle = Bundle()
        bundle.putSerializable(IngredientsSelectorActivity.EXTRA_CUSTOM_INGREDIENTS, mapIngredientQuantity)

        val returnIntent = Intent()
        returnIntent.putExtra(IngredientsSelectorActivity.EXTRA_BUNDLE, bundle)
        view.returnDataAndClose(Activity.RESULT_OK, returnIntent)
    }
}