package br.panaggio.restaurantapp.features.sandwichDetails.presenter

import android.util.Log
import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.domain.useCases.CreateOrderItemUseCase
import br.panaggio.restaurantapp.domain.useCases.FetchSandwichUseCase
import br.panaggio.restaurantapp.features.sandwichDetails.SandwichDetailsContract
import br.panaggio.restaurantapp.utils.PriceCalculator
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class SandwichDetailsPresenter(
        val view: SandwichDetailsContract.View,
        val fetchSandwichUseCase: FetchSandwichUseCase,
        val createOrderItemUseCase: CreateOrderItemUseCase,
        val uiScheduler: Scheduler) {

    private val subscriptions: CompositeDisposable by lazy { CompositeDisposable() }
    private lateinit var sandwich: Sandwich
    private var extrasIngredientsQuantity = HashMap<Ingredient, Int>()

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
        val extrasIngredient = getExtraIngredientsList()
        val orderItem = OrderItem(0, sandwich, extrasIngredient, 0)
        val price = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        view.displaySandwich(sandwich, price, extrasIngredient)
    }

    fun clickedOrder() {
        createOrderItemUseCase
                .execute(sandwich.id, getExtraIngredientsList())
                .observeOn(uiScheduler)
                .doOnSubscribe { view.showLoading() }
                .subscribe({ view.close() }, { view.showCreatingOrderError(it) })
    }

    fun clickCustom() {
        view.openIngredientsSelector()
    }

    fun extraUpdated(extras: HashMap<Ingredient, Int>?) {
        extrasIngredientsQuantity = extras ?: HashMap()

        val extrasIngredient = getExtraIngredientsList()
        val orderItem = OrderItem(0, sandwich, extrasIngredient, 0)
        val price = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        view.displaySandwich(sandwich, price, extrasIngredient)
    }

    private fun getExtraIngredientsList() : List<Ingredient> {
        return extrasIngredientsQuantity.entries.flatMap {
            val amount = it.value
            val ingredient = it.key
            (1..amount).map { ingredient }
        }
    }
}