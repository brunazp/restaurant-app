package br.panaggio.restaurantapp.features.sandwichDetails.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Sandwich

class SandwichDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandwich_details)
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
