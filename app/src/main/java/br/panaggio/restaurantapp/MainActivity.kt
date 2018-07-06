package br.panaggio.restaurantapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import br.panaggio.restaurantapp.features.offersList.view.OffersListFragment
import br.panaggio.restaurantapp.features.sandwichesList.view.SandwichesListFragment
import br.panaggio.restaurantapp.features.shoppingCart.view.ShoppingCartFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displaySandwichList()

        bottom_navigation.setOnNavigationItemSelectedListener {
            navigationItemSelected(it)
        }
    }

    private fun navigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sandwich_list -> displaySandwichList()
        R.id.action_offers_list -> displayOffersList()
        R.id.action_cart -> displayShoppingCart()
        else -> false
    }

    private fun displaySandwichList(): Boolean {
        val sandwichesListFragment = SandwichesListFragment.newInstance()
        openFragment(sandwichesListFragment)
        setToolbarTitle(R.string.sandwiches_label)
        return true
    }

    private fun displayOffersList(): Boolean {
        val offersListFragment = OffersListFragment.newInstance()
        openFragment(offersListFragment)
        setToolbarTitle(R.string.offers_label)
        return true
    }

    private fun displayShoppingCart(): Boolean {
        val shoppingCartFragment = ShoppingCartFragment.newInstance()
        openFragment(shoppingCartFragment)
        setToolbarTitle(R.string.cart_label)
        return true
    }

    private fun setToolbarTitle(titleRes : Int) {
        supportActionBar?.setTitle(titleRes)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
