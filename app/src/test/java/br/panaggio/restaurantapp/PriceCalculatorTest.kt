package br.panaggio.restaurantapp

import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.utils.PriceCalculator
import junit.framework.Assert.assertEquals
import org.junit.Test

class PriceCalculatorTest {
    @Test
    fun testSandwichPrice() {
        val sandwichPrice = PriceCalculator.calculateSandwichPrice(SANDWICH_CHEESE_BURGER)
        assertEquals(CHEESE_BURGER_EXPECTED_PRICE, sandwichPrice)
    }

    @Test
    fun testOrderItemPriceWithoutExtras() {
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, emptyList(), 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(CHEESE_BURGER_EXPECTED_PRICE, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceWithExtras() {
        val extraIngredients = listOf(INGREDIENT_BREAD)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(6.5, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceLightOfferWithoutBacon() {
        val extraIngredients = listOf(INGREDIENT_LETTUCE)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(5.31, orderItemPrice, 0.01)
    }

    @Test
    fun testOrderItemPriceLightOfferWithBacon() {
        val extraIngredients = listOf(INGREDIENT_LETTUCE, INGREDIENT_BACON)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(7.9, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceBurgerOfferWith2Burgers() {
        val extraIngredients = listOf(INGREDIENT_BURGER)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(8.5, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceBurgerOfferWith3Burgers() {
        val extraIngredients = listOf(INGREDIENT_BURGER, INGREDIENT_BURGER)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(8.5, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceBurgerOfferWith4Burgers() {
        val extraIngredients = listOf(INGREDIENT_BURGER, INGREDIENT_BURGER, INGREDIENT_BURGER)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(11.5, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceBurgerOfferWith99Burgers() {
        val extraIngredients = (1..98).map { INGREDIENT_BURGER }
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(200.5, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceCheeseOfferWith2Cheese() {
        val extraIngredients = listOf(INGREDIENT_CHEESE)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(7.0, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceCheeseOfferWith3Cheese() {
        val extraIngredients = listOf(INGREDIENT_CHEESE, INGREDIENT_CHEESE)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(7.0, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceCheeseOfferWith4Cheese() {
        val extraIngredients = listOf(INGREDIENT_CHEESE, INGREDIENT_CHEESE, INGREDIENT_CHEESE)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(8.5, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceCheeseOfferWith99Cheese() {
        val extraIngredients = (1..98).map { INGREDIENT_CHEESE }
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(103.0, orderItemPrice)
    }

    @Test
    fun testOrderItemPriceWithAllOffers() {
        val extraIngredients = listOf(INGREDIENT_CHEESE, INGREDIENT_CHEESE,
                INGREDIENT_BURGER, INGREDIENT_BURGER, INGREDIENT_LETTUCE)
        val orderItem = OrderItem(1, SANDWICH_CHEESE_BURGER, extraIngredients, 0)
        val orderItemPrice = PriceCalculator.calculateOrderItemPriceWithOffer(orderItem)
        assertEquals(9.36, orderItemPrice, 0.01)
    }
    
    companion object {
        private val FAKE_URL = "http://google.com"
        private val INGREDIENT_BACON = Ingredient(2, "Bacon", 2.0, "")
        private val INGREDIENT_BURGER = Ingredient(3, "Burger", 3.0, "")
        private val INGREDIENT_LETTUCE = Ingredient(1, "Lettuce", 0.4, "")
        private val INGREDIENT_CHEESE = Ingredient(5, "Cheese", 1.5, "")
        private val INGREDIENT_BREAD = Ingredient(6, "Bread", 1.0, "")
        private val CHEESE_BURGER_INGREDIENTS = listOf(
                INGREDIENT_BREAD,
                INGREDIENT_CHEESE,
                INGREDIENT_BURGER
        )
        private val CHEESE_BURGER_EXPECTED_PRICE = 5.5
        private val SANDWICH_CHEESE_BURGER = Sandwich(1, "X-Burger", FAKE_URL, CHEESE_BURGER_INGREDIENTS)
    }
}