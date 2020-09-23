package com.example.testapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.data.CatFact
import com.example.testapp.model.service.ServiceBuilder
import com.example.testapp.view.adapters.CatFactsAdapter
import com.example.testapp.viewmodel.CatFactViewModel

class Fragment2 : Fragment() {
    lateinit var catFactViewModel: CatFactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {}}
    }

    fun loadCatFacts() {
        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = CatFactsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        catFactViewModel = CatFactViewModel(ServiceBuilder)
        catFactViewModel.fetchFact()
        catFactViewModel.catFacts.observe(this, Observer { catFacts ->
            catFacts?.let { adapter.setCatFacts(it) }
        })
    }

    fun refreshCatFacts() {
        catFactViewModel.refresh()
    }

}