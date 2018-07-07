package br.panaggio.restaurantapp

import android.app.Application
import br.panaggio.restaurantapp.di.Injection
import com.github.salomonbrys.kodein.KodeinAware

class RestaurantApplication : Application(), KodeinAware {
    override val kodein by Injection(this).graph
}