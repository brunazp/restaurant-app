package br.panaggio.restaurantapp.features.sandwichesList.presenter

import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.features.sandwichesList.SandwichesListContract

class SandwichesListPresenter(val view: SandwichesListContract.View) {
    fun loadSandwiches() {
        val sandwiches = listOf(
                Sandwich(1, "X-Burger1", "https://goo.gl/W9WdaF"),
                Sandwich(2, "X-Burger2", "https://goo.gl/W9WdaF"),
                Sandwich(3, "X-Burger3", "https://goo.gl/W9WdaF"),
                Sandwich(4, "X-Burger4", "https://goo.gl/9DhCgk"),
                Sandwich(5, "X-Burger5", "https://goo.gl/9DhCgk"),
                Sandwich(6, "X-Burger6", "https://goo.gl/9DhCgk"),
                Sandwich(7, "X-Burger7", "https://goo.gl/9DhCgk"),
                Sandwich(8, "X-Burger8", "https://goo.gl/9DhCgk"),
                Sandwich(9, "X-Burger9", "https://goo.gl/9DhCgk"),
                Sandwich(10, "X-Burger10", "https://goo.gl/9DhCgk"),
                Sandwich(11, "X-Burger11", "https://goo.gl/9DhCgk"),
                Sandwich(12, "X-Burger12", "https://goo.gl/9DhCgk")
        )

        view.displaySandwiches(sandwiches)
    }

}