package br.panaggio.restaurantapp.features.offersList.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Offer
import br.panaggio.restaurantapp.features.offersList.OffersListContract
import br.panaggio.restaurantapp.features.offersList.presenter.OffersListPresenter
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.fragment_offers_list.*
import kotlinx.android.synthetic.main.progress_bar.*

class OffersListFragment : Fragment(), OffersListContract.View {
    private lateinit var offersListAdapter: OffersListAdapter
    private val kodein by lazy { LazyKodein(appKodein) }
    private val presenter by kodein.with(this).instance<OffersListPresenter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offers_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadOffers()
    }

    override fun onPause() {
        super.onPause()
        presenter.release()
    }

    private fun setupRecyclerView() {
        offersListAdapter = OffersListAdapter()
        recyclerview_offers.apply {
            adapter = offersListAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        }
    }

    override fun displayOffers(offers: List<Offer>) {
        recyclerview_offers.visibility = View.VISIBLE
        offersListAdapter.setItems(offers)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun displayError(error: Throwable) {
        textview_error_message.visibility = View.VISIBLE
    }

    override fun displayEmpty() {
        textview_empty_message.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = OffersListFragment()
    }
}