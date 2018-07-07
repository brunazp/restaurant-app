package br.panaggio.restaurantapp.features.sandwichesList.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Sandwich
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_sandwich.view.*
import java.text.NumberFormat

class SandwichesListAdapter(
        private var itemClickListener: (Sandwich) -> Unit,
        private var sandwiches: List<Sandwich> = emptyList()) : RecyclerView.Adapter<SandwichItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SandwichItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_sandwich, parent, false)
        return SandwichItemViewHolder(view)
    }

    override fun getItemCount() = sandwiches.size

    override fun onBindViewHolder(holder: SandwichItemViewHolder, position: Int) {
        val sandwich = sandwiches[position]
        holder.bindView(sandwich, itemClickListener)
    }

    fun setItems(newSandwiches: List<Sandwich>) {
        sandwiches = newSandwiches
        notifyDataSetChanged()
    }
}

class SandwichItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bindView(sandwich: Sandwich, itemClickListener: (Sandwich) -> Unit) {
        itemView.textview_name.text = sandwich.name

        val requestOptions = RequestOptions().placeholder(R.drawable.sandwiches)
        Glide.with(itemView.rootView.context)
                .load(sandwich.photoUrl)
                .apply(requestOptions)
                .into(itemView.imageview_photo)

        itemView.textview_ingredients.text = sandwich.ingredients.joinToString { it.name }

        val sandwichPrice = sandwich.ingredients.sumByDouble { it.price }
        itemView.textview_price.text = NumberFormat.getCurrencyInstance().format(sandwichPrice)

        itemView.setOnClickListener { itemClickListener(sandwich) }
    }
}