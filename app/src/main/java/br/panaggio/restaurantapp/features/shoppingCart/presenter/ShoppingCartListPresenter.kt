package br.panaggio.restaurantapp.features.shoppingCart.presenter

import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.domain.useCases.FetchOrderItemsUseCase
import br.panaggio.restaurantapp.features.shoppingCart.ShoppingCartContract
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class ShoppingCartListPresenter(
        val view: ShoppingCartContract.View,
        val fetchOrderItemsUseCase: FetchOrderItemsUseCase,
        val uiScheduler: Scheduler) {
    private val subscriptions: CompositeDisposable by lazy { CompositeDisposable() }

    fun loadOrderItems() {
        val subscription = fetchOrderItemsUseCase
                .execute()
                .subscribeOn(uiScheduler)
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

    private fun displayItems(orderItems: List<OrderItem>) {
        if (orderItems.isEmpty()) {
            view.displayEmpty()
        } else {
            view.displayOrderItems(orderItems)
        }
    }
}