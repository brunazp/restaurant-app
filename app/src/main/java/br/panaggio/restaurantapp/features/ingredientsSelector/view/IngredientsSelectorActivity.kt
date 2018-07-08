package br.panaggio.restaurantapp.features.ingredientsSelector.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Ingredient
import br.panaggio.restaurantapp.features.ingredientsSelector.IngredientsSelectorContract
import br.panaggio.restaurantapp.features.ingredientsSelector.presenter.IngredientsSelectorPresenter
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import kotlinx.android.synthetic.main.activity_ingredients_selector.*

class IngredientsSelectorActivity : AppCompatActivity(), IngredientsSelectorContract.View {
    private lateinit var ingredientsListAdapter: IngredientsListAdapter
    private val kodein by lazy { LazyKodein(appKodein) }
    private val presenter by kodein.with(this).instance<IngredientsSelectorPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients_selector)
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_ingredients_selector, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        when (item?.itemId) {
            R.id.action_complete_selection -> presenter.clickedOnCompleteSelection()
        }
        return true
    }

    private fun setupRecyclerView() {
        ingredientsListAdapter = IngredientsListAdapter(
                presenter::clickedOnIncreaseIngredient, presenter::clickedOnDecreaseIngredient)
        recyclerview_ingredients.apply {
            adapter = ingredientsListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadIngredients()
    }

    override fun onPause() {
        super.onPause()
        presenter.release()
    }

    override fun displayItems(ingredientsQuantity: Map<Ingredient, Int>) {
        recyclerview_ingredients.visibility = View.VISIBLE
        ingredientsListAdapter.setItems(ingredientsQuantity)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun displayError(error: Throwable) {
        textview_error_message.visibility = View.VISIBLE
    }

    override fun displayEmpty() {
        textview_empty_message.visibility = View.VISIBLE
    }

    override fun returnDataAndClose(result: Int, returnIntent: Intent) {
        setResult(result, returnIntent);
        finish()
    }

    companion object {
        val EXTRA_ITEMS_REQUEST = 1
        val EXTRA_BUNDLE = "extra_bundle"
        val EXTRA_CUSTOM_INGREDIENTS = "extra_result_ingredients"
    }
}
