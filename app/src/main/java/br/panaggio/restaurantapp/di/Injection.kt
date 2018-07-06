package br.panaggio.restaurantapp.di

import android.app.Application
import br.panaggio.restaurantapp.domain.dataSources.RestaurantApiDataSource
import br.panaggio.restaurantapp.domain.useCases.FetchSandwichesListUseCase
import br.panaggio.restaurantapp.features.sandwichesList.SandwichesListContract
import br.panaggio.restaurantapp.features.sandwichesList.presenter.SandwichesListPresenter
import br.panaggio.restaurantapp.features.sandwichesList.useCases.FetchSandwichesList
import br.panaggio.restaurantapp.features.sandwichesList.view.SandwichesListFragment
import br.panaggio.restaurantapp.network.RetrofitRestaurantApiDataSource
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.github.salomonbrys.kodein.android.androidFragmentScope
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope

class Injection(private val application: Application) {
    init {
        application.registerActivityLifecycleCallbacks(androidActivityScope.lifecycleManager)
    }

    val graph = Kodein.lazy {
        bind<RestaurantApiDataSource>() with singleton {
            RetrofitRestaurantApiDataSource()
        }

        bind<FetchSandwichesListUseCase>() with singleton {
            FetchSandwichesList(
                    restaurantApiDataSource = instance()
            )
        }

        bind<SandwichesListPresenter>() with scopedSingleton(androidSupportFragmentScope) {
            SandwichesListPresenter(
                    view = it as SandwichesListContract.View,
                    fetchSandwichesListUseCase = instance()
            )
        }
    }
}