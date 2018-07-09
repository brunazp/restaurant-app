package br.panaggio.restaurantapp.utils

import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.domain.entities.Sandwich

class PriceCalculator {

    companion object {
        private const val BURGER_ID = 3
        private const val BURGER_OFFER_AMOUNT = 3
        private const val CHEESE_ID = 5
        private const val CHEESE_OFFER_AMOUNT = 3
        private const val LETTUCE_ID = 1
        private const val BACON_ID = 2

        fun calculateOrderItemPriceWithOffer(orderItem: OrderItem): Double {
            with(orderItem) {
                val sandwichIngredients = sandwich?.ingredients ?: emptyList()
                val allIngredients = (sandwichIngredients + extras).toMutableList()

                // Checking Burger offer (buying 3, you pay for 2)
                val hamburgerCount = allIngredients.count { it.id == BURGER_ID }
                val hamburgersToRemove = hamburgerCount / BURGER_OFFER_AMOUNT
                (1..hamburgersToRemove).forEach {
                    val position = allIngredients.indexOfFirst { it.id == BURGER_ID }
                    if (position != -1) {
                        allIngredients.removeAt(position)
                    }
                }

                // Checking Cheese offer (buying 3, you pay for 2)
                val cheeseCount = allIngredients.count { it.id == CHEESE_ID }
                val cheeseToRemove = cheeseCount / CHEESE_OFFER_AMOUNT
                (1..cheeseToRemove).forEach {
                    val position = allIngredients.indexOfFirst { it.id == CHEESE_ID }
                    if (position != -1) {
                        allIngredients.removeAt(position)
                    }
                }

                // Checking Light Offer (have lettuce and don't have bacon = 10% discount)
                val hasLettuce = allIngredients.count { it.id == LETTUCE_ID } != 0
                val hasBacon = allIngredients.count { it.id == BACON_ID } != 0
                val applyDiscount = hasLettuce && !hasBacon

                val orderPrice = allIngredients.sumByDouble { it.price }

                return if (applyDiscount) orderPrice * 0.9 else orderPrice
            }
        }

        fun calculateSandwichPrice(sandwich: Sandwich) = sandwich.ingredients.sumByDouble { it.price }
    }
}