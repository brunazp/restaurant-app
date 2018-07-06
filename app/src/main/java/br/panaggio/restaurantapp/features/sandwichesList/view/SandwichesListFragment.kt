package br.panaggio.restaurantapp.features.sandwichesList.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Sandwich
import br.panaggio.restaurantapp.features.sandwichesList.SandwichesListContract
import br.panaggio.restaurantapp.features.sandwichesList.presenter.SandwichesListPresenter
import kotlinx.android.synthetic.main.fragment_sandwiches_list.*

class SandwichesListFragment : Fragment(), SandwichesListContract.View {
    private lateinit var sandwichesListAdapter: SandwichesListAdapter
    private lateinit var presenter: SandwichesListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sandwiches_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        sandwichesListAdapter = SandwichesListAdapter()
        recyclerview_sandwiches.apply {
            adapter = sandwichesListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter = SandwichesListPresenter(this)
        presenter.loadSandwiches()
    }

    override fun displaySandwiches(sandwiches: List<Sandwich>) {
        sandwichesListAdapter.setItems(sandwiches)
    }

    override fun showLoading() {
        //TODO: show loading
    }

    override fun hideLoading() {
        //TODO: hide loading
    }

    override fun displayError(error: Throwable) {
        //TODO: display errors
    }

    override fun displayEmpty() {
        //TODO: display empty screen
    }

    companion object {
        fun newInstance() = SandwichesListFragment()
    }
}