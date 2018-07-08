package br.panaggio.restaurantapp.features.ingredientsSelector.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
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

    private fun setupRecyclerView() {
        ingredientsListAdapter = IngredientsListAdapter()
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

    override fun displayItems(ingredients: List<Ingredient>) {
        recyclerview_ingredients.visibility = View.VISIBLE
        ingredientsListAdapter.setItems(ingredients)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun displayError(error: Throwable) {

    }

    override fun displayEmpty() {

    }


    companion object {
        fun navigateHere(context: Context?) {
            val intent = Intent(context, IngredientsSelectorActivity::class.java)
            context?.startActivity(intent)
        }
    }
}
