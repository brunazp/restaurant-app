package br.panaggio.restaurantapp.features.shoppingCart.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.OrderItem
import br.panaggio.restaurantapp.features.shoppingCart.ShoppingCartContract
import br.panaggio.restaurantapp.features.shoppingCart.presenter.ShoppingCartListPresenter
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingCartFragment : Fragment(), ShoppingCartContract.View {
    private val kodein by lazy { LazyKodein(appKodein) }
    private val presenter by kodein.with(this).instance<ShoppingCartListPresenter>()
    private lateinit var orderItemListAdapter: OrderItemListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        orderItemListAdapter = OrderItemListAdapter()
        recyclerview_order_items.apply {
            adapter = orderItemListAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadOrderItems()
    }

    override fun onPause() {
        super.onPause()
        presenter.release()
    }

    override fun displayOrderItems(offers: List<OrderItem>) {
        recyclerview_order_items.visibility = View.VISIBLE
        orderItemListAdapter.setItems(offers)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun displayError(error: Throwable) {
        textview_error_message.visibility = View.GONE
    }

    override fun displayEmpty() {
        textview_empty_message.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = ShoppingCartFragment()
    }
}