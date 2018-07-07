package br.panaggio.restaurantapp.features.offersList.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Offer
import kotlinx.android.synthetic.main.item_offer.view.*
import kotlinx.android.synthetic.main.item_sandwich.view.*

class OffersListAdapter(
        private var offers: List<Offer> = emptyList()) : RecyclerView.Adapter<OfferItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_offer, parent, false)
        return OfferItemViewHolder(view)
    }

    override fun getItemCount() = offers.size

    override fun onBindViewHolder(holder: OfferItemViewHolder, position: Int) {
        val sandwich = offers[position]
        holder.bindView(sandwich)    }

}

class OfferItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bindView(offer: Offer) {
        itemView.textview_offer_name.text = offer.name
        itemView.textview_description.text = offer.description
    }
}