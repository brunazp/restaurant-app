package br.panaggio.restaurantapp.features.shoppingCart.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.OrderItem

class OrderItemListAdapter(
        private var orderItems: List<OrderItem> = emptyList()) : RecyclerView.Adapter<OrderItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_order, parent, false)
        return OrderItemViewHolder(view)
    }

    override fun getItemCount() = orderItems.size

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        val orderItem = orderItems[position]
        holder.bindView(orderItem)
    }

    fun setItems(newOrderItems: List<OrderItem>) {
        orderItems = newOrderItems
        notifyDataSetChanged()
    }
}

class OrderItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bindView(orderItem: OrderItem) {

    }
}