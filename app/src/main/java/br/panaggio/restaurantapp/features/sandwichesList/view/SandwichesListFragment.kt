package br.panaggio.restaurantapp.features.sandwichesList.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R
import br.panaggio.restaurantapp.domain.entities.Sandwich
import kotlinx.android.synthetic.main.fragment_sandwiches_list.*

class SandwichesListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sandwiches_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val sandwiches = listOf(
                Sandwich(1, "X-Burger1", "https://goo.gl/W9WdaF"),
                Sandwich(2, "X-Burger2", "https://goo.gl/W9WdaF"),
                Sandwich(3, "X-Burger3", "https://goo.gl/W9WdaF"),
                Sandwich(4, "X-Burger4", "https://goo.gl/9DhCgk"),
                Sandwich(5, "X-Burger5", "https://goo.gl/9DhCgk"),
                Sandwich(6, "X-Burger6", "https://goo.gl/9DhCgk"),
                Sandwich(7, "X-Burger7", "https://goo.gl/9DhCgk"),
                Sandwich(8, "X-Burger8", "https://goo.gl/9DhCgk"),
                Sandwich(9, "X-Burger9", "https://goo.gl/9DhCgk"),
                Sandwich(10, "X-Burger10", "https://goo.gl/9DhCgk"),
                Sandwich(11, "X-Burger11", "https://goo.gl/9DhCgk"),
                Sandwich(12, "X-Burger12", "https://goo.gl/9DhCgk")
        )

        val sandwichesListAdapter = SandwichesListAdapter(sandwiches)
        recyclerview_sandwiches.apply {
            adapter = sandwichesListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    companion object {
        fun newInstance() = SandwichesListFragment()
    }
}