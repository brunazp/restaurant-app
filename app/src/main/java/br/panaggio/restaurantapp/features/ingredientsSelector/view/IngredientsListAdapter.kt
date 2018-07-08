package br.panaggio.restaurantapp.features.ingredientsSelector.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Ingredient
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_ingredient.view.*
import java.text.NumberFormat

class IngredientsListAdapter(
        private var itemPlusClickListener: (Ingredient) -> Unit,
        private var itemMinusClickListener: (Ingredient) -> Unit,
        private var ingredientsQuantity: Map<Ingredient, Int> = emptyMap()) : RecyclerView.Adapter<IngredientItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_ingredient, parent, false)
        return IngredientItemViewHolder(view)
    }

    override fun getItemCount() = ingredientsQuantity.size

    override fun onBindViewHolder(holder: IngredientItemViewHolder, position: Int) {
        val ingredient = ingredientsQuantity.keys.elementAt(position)
        val quantity = ingredientsQuantity.values.elementAt(position)
        holder.bindView(ingredient, quantity, itemPlusClickListener, itemMinusClickListener)
    }

    fun setItems(newIngredients: Map<Ingredient, Int>) {
        ingredientsQuantity = newIngredients
        notifyDataSetChanged()
    }
}

class IngredientItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bindView(
            ingredient: Ingredient,
            quantity: Int,
            itemPlusClickListener: (Ingredient) -> Unit,
            itemMinusClickListener: (Ingredient) -> Unit) {
        val requestOptions = RequestOptions().placeholder(R.drawable.sandwiches)
        Glide.with(itemView.rootView.context)
                .load(ingredient.photoUrl)
                .apply(requestOptions)
                .into(itemView.imageview_photo)

        itemView.textview_name.text = ingredient.name
        itemView.textview_price.text = NumberFormat.getCurrencyInstance().format(ingredient.price)
        itemView.textview_quantity.text = quantity.toString()
        itemView.imagebutton_minus.setOnClickListener { itemMinusClickListener(ingredient) }
        itemView.imagebutton_plus.setOnClickListener { itemPlusClickListener(ingredient) }


        val totalPrice = quantity * ingredient.price
        itemView.textview_total_price.text = NumberFormat.getCurrencyInstance().format(totalPrice)
    }
}