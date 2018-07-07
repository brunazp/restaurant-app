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
import kotlinx.android.synthetic.main.fragment_offers_list.*

class OffersListFragment : Fragment() {
    private lateinit var offersListAdapter: OffersListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offers_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val offers = listOf(
                Offer(10, "Name 16", "Description1"),
                Offer(21, "Name 16", "Description1"),
                Offer(31, "Name 154", "Description1"),
                Offer(41, "Name 13", "Description1"),
                Offer(51, "Name 12", "Description1"),
                Offer(61, "Name 11", "Description1"),
                Offer(71, "Name 11", "Description1"),
                Offer(81, "Name 11", "Description1"),
                Offer(91, "Name 12", "Description1"),
                Offer(11, "Name 11", "Description1")
                )

        offersListAdapter = OffersListAdapter(offers)
        recyclerview_offers.apply {
            adapter = offersListAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        }
    }

    companion object {
        fun newInstance() = OffersListFragment()
    }
}