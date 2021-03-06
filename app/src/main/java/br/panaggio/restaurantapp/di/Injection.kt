package br.panaggio.restaurantapp.di

import android.app.Application
import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.useCases.*
import br.panaggio.restaurantapp.features.ingredientsSelector.IngredientsSelectorContract
import br.panaggio.restaurantapp.features.ingredientsSelector.presenter.IngredientsSelectorPresenter
import br.panaggio.restaurantapp.features.ingredientsSelector.useCases.FetchIngredientsList
import br.panaggio.restaurantapp.features.offersList.OffersListContract
import br.panaggio.restaurantapp.features.offersList.presenter.OffersListPresenter
import br.panaggio.restaurantapp.features.offersList.useCases.FetchOffersList
import br.panaggio.restaurantapp.features.sandwichDetails.SandwichDetailsContract
import br.panaggio.restaurantapp.features.sandwichDetails.presenter.SandwichDetailsPresenter
import br.panaggio.restaurantapp.features.sandwichDetails.useCases.CreateOrderItem
import br.panaggio.restaurantapp.features.sandwichDetails.useCases.FetchSandwich
import br.panaggio.restaurantapp.features.sandwichesList.SandwichesListContract
import br.panaggio.restaurantapp.features.sandwichesList.presenter.SandwichesListPresenter
import br.panaggio.restaurantapp.features.sandwichesList.useCases.FetchSandwichesList
import br.panaggio.restaurantapp.features.shoppingCart.ShoppingCartContract
import br.panaggio.restaurantapp.features.shoppingCart.presenter.ShoppingCartListPresenter
import br.panaggio.restaurantapp.features.shoppingCart.useCases.FetchOrderItems
import br.panaggio.restaurantapp.network.restaurantApi.RestaurantApiService
import br.panaggio.restaurantapp.network.restaurantApi.RetrofitRestaurantApiDataSource
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Injection(private val application: Application) {
    init {
        application.registerActivityLifecycleCallbacks(androidActivityScope.lifecycleManager)
    }

    val graph = Kodein.lazy {
        bind<Scheduler>(WORKER_SCHEDULER) with singleton {
            Schedulers.io()
        }

        bind<Scheduler>(UI_SCHEDULER) with singleton {
            AndroidSchedulers.mainThread()
        }

        bind<Retrofit>() with singleton {
            Retrofit.Builder()
                    .baseUrl(RestaurantApiService.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        bind<RestaurantApiService>() with singleton {
            val retrofit: Retrofit = instance()
            retrofit.create(RestaurantApiService::class.java)
        }

        bind<RestaurantApiDataSource>() with singleton {
            RetrofitRestaurantApiDataSource(
                    restaurantApiService = instance()
            )
        }

        bind<FetchSandwichesListUseCase>() with singleton {
            FetchSandwichesList(
                    restaurantApiDataSource = instance(),
                    scheduler = instance(WORKER_SCHEDULER)
            )
        }

        bind<SandwichesListPresenter>() with scopedSingleton(androidSupportFragmentScope) {
            SandwichesListPresenter(
                    view = it as SandwichesListContract.View,
                    fetchSandwichesListUseCase = instance(),
                    uiScheduler = instance(UI_SCHEDULER)
            )
        }

        bind<FetchOffersListUseCase>() with singleton {
            FetchOffersList(
                    restaurantApiDataSource = instance(),
                    scheduler = instance(WORKER_SCHEDULER)
            )
        }

        bind<OffersListPresenter>() with scopedSingleton(androidSupportFragmentScope) {
            OffersListPresenter(
                    view = it as OffersListContract.View,
                    fetchOffersListUseCase = instance(),
                    uiScheduler = instance(UI_SCHEDULER)
            )
        }

        bind<FetchSandwichUseCase>() with singleton {
            FetchSandwich(
                    restaurantApiDataSource = instance(),
                    scheduler = instance(WORKER_SCHEDULER)
            )
        }

        bind<CreateOrderItemUseCase>() with singleton {
            CreateOrderItem(
                    restaurantApiDataSource = instance(),
                    scheduler = instance(WORKER_SCHEDULER)
            )
        }

        bind<SandwichDetailsPresenter>() with scopedSingleton(androidActivityScope) {
            SandwichDetailsPresenter(
                    view = it as SandwichDetailsContract.View,
                    fetchSandwichUseCase = instance(),
                    createOrderItemUseCase = instance(),
                    uiScheduler = instance(UI_SCHEDULER)
            )
        }

        bind<FetchOrderItemsUseCase>() with singleton {
            FetchOrderItems(
                    restaurantApiDataSource = instance(),
                    scheduler = instance(WORKER_SCHEDULER)
            )
        }

        bind<ShoppingCartListPresenter>() with scopedSingleton(androidSupportFragmentScope) {
            ShoppingCartListPresenter(
                    view = it as ShoppingCartContract.View,
                    fetchOrderItemsUseCase = instance(),
                    uiScheduler = instance(UI_SCHEDULER)
            )
        }

        bind<FetchIngredientsListUseCase>() with singleton {
            FetchIngredientsList(
                    restaurantApiDataSource = instance(),
                    scheduler = instance(WORKER_SCHEDULER)
            )
        }

        bind<IngredientsSelectorPresenter>() with scopedSingleton(androidActivityScope) {
            IngredientsSelectorPresenter(
                    view = it as IngredientsSelectorContract.View,
                    fetchIngredientsList = instance(),
                    uiScheduler = instance(UI_SCHEDULER)
            )
        }
    }

    companion object {
        const val WORKER_SCHEDULER = "worker"
        const val UI_SCHEDULER = "ui"
    }
}