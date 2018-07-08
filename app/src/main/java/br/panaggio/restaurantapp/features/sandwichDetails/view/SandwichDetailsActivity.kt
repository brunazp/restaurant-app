package br.panaggio.restaurantapp.features.sandwichDetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
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

    override fun onPause() {
        super.onPause()
        presenter.release()
    }

    override fun displaySandwich(sandwich: Sandwich, price: Double) {
        showContent(true)
        val requestOptions = RequestOptions().placeholder(R.drawable.sandwiches)
        Glide.with(this)
                .load(sandwich.photoUrl)
                .apply(requestOptions)
                .into(imageview_photo)
        textview_name.text = sandwich.name
        textview_ingredients.text = sandwich.ingredients.joinToString(separator = "\n") { it.name }
        textview_price.text = NumberFormat.getCurrencyInstance().format(price)
        button_order.setOnClickListener { presenter.clickedOrder() }
    }

    override fun displayError(error: Throwable) {
        showContent(false)
        textview_error_message.visibility = View.VISIBLE
    }

    override fun showLoading() {
        showContent(false)
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        showContent(true)
        progress_bar.visibility = View.GONE
    }

    override fun close() {
        finish()
    }

    override fun showCreatingOrderError() {
        AlertDialog.Builder(this)
                .setMessage(R.string.generic_list_error_message)
                .setPositiveButton(android.R.string.ok, { _, _ -> finish() })
                .show()
    }

    private fun showContent(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        imageview_photo.visibility = visibility
        textview_name.visibility = visibility
        textview_ingredients_label.visibility = visibility
        textview_ingredients.visibility = visibility
        textview_price.visibility = visibility
        button_order.visibility = visibility
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
