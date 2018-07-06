package br.panaggio.restaurantapp.features.sandwichesList.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Sandwich
import kotlinx.android.synthetic.main.item_sandwich.view.*

class SandwichesListAdapter(
        private val sandwiches: List<Sandwich> = emptyList()) : RecyclerView.Adapter<SandwichItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SandwichItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val view = layoutInflater.inflate(R.layout.item_sandwich, parent, false)
        return SandwichItemViewHolder(view)
    }

    override fun getItemCount() = sandwiches.size

    override fun onBindViewHolder(holder: SandwichItemViewHolder?, position: Int) {
        val sandwich = sandwiches[position]
        holder?.bindView(sandwich)
    }
}

class SandwichItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bindView(sandwich: Sandwich) {
        itemView.textview_name.text = sandwich.name
    }
}