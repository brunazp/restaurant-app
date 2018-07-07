package br.panaggio.restaurantapp.features.sandwichDetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.features.sandwichDetails.SandwichDetailsContract
import br.panaggio.restaurantapp.features.sandwichDetails.presenter.SandwichDetailsPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import kotlinx.android.synthetic.main.activity_sandwich_details.*
import java.text.NumberFormat

class SandwichDetailsActivity : AppCompatActivity(), SandwichDetailsContract.View {
    private val kodein by lazy { LazyKodein(appKodein) }
    private val presenter by kodein.with(this).instance<SandwichDetailsPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandwich_details)
    }

    override fun onResume() {
        super.onResume()
        val sandwichId = intent.extras.getInt(EXTRA_SANDWICH_ID)
        presenter.loadSandwich(sandwichId)
    }

    override fun displaySandwich(sandwich: Sandwich, price: Double) {
        val requestOptions = RequestOptions().placeholder(R.drawable.sandwiches)
        Glide.with(this)
                .load(sandwich.photoUrl)
                .apply(requestOptions)
                .into(imageview_photo)
        textview_name.text = sandwich.name
        textview_ingredients.text = sandwich.ingredients.joinToString(separator = "\n") { it.name }
        textview_price.text = NumberFormat.getCurrencyInstance().format(price)
    }

    override fun displayError(error: Throwable) {

    }

    companion object {
        val EXTRA_SANDWICH_ID = "sandwich_id"
        fun navigateHere(context: Context?, sandwich: Sandwich) {
            val intent = Intent(context, SandwichDetailsActivity::class.java)
            intent.putExtra(EXTRA_SANDWICH_ID, sandwich.id)
            context?.startActivity(intent)
        }
    }
}
