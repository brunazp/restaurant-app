package br.panaggio.restaurantapp.features.ingredientsSelector.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.panaggio.restaurantapp.R

class IngredientsSelectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients_selector)
    }

    companion object {
        fun navigateHere(context: Context?) {
            val intent = Intent(context, IngredientsSelectorActivity::class.java)
            context?.startActivity(intent)
        }
    }
}
