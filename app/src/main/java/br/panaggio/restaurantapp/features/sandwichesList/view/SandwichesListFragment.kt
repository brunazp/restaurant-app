package br.panaggio.restaurantapp.features.sandwichesList.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.panaggio.restaurantapp.R

class SandwichesListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_sandwiches_list, container, false)
    }

    companion object {
        fun newInstance() = SandwichesListFragment()
    }
}