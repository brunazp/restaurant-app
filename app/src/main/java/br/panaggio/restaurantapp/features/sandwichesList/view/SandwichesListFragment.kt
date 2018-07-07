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
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import kotlinx.android.synthetic.main.fragment_sandwiches_list.*

class SandwichesListFragment : Fragment(), SandwichesListContract.View {
    private val kodein by lazy { LazyKodein(appKodein) }
    private lateinit var sandwichesListAdapter: SandwichesListAdapter
    private val presenter by kodein.with(this).instance<SandwichesListPresenter>()

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
        presenter.loadSandwiches()
    }

    override fun onPause() {
        super.onPause()
        presenter.release()
    }

    override fun displaySandwiches(sandwiches: List<Sandwich>) {
        recyclerview_sandwiches.visibility = View.VISIBLE
        sandwichesListAdapter.setItems(sandwiches)
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
        fun newInstance() = SandwichesListFragment()
    }
}