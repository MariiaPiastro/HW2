package com.geekhub.mariia_piastro.hw2.populardesserts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_desserts_list_fragment.view.*

class DessertsListFragment : Fragment() {

    private val dessertAdapter by lazy { DessertAdapter(desserts) }
    private var desserts: ArrayList<Desserts> = ArrayList()

    companion object {
        fun newInstance(callback: DessertAdapter.Callback): DessertsListFragment {
            val fragment = DessertsListFragment()
            fragment.setCallback(callback)
            return fragment
        }
    }

    fun setCallback(callback: DessertAdapter.Callback) = dessertAdapter.setCallback(callback)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        createDessert()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(R.layout.activity_desserts_list_fragment, container, false)
        val activity = activity as Context
        val recyclerView: RecyclerView = view.recyclerView as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = dessertAdapter
        return view
    }

    private fun createDessert() {
        val titles = resources.getStringArray(R.array.desserts)
        val dessertsInfo = resources.getStringArray(R.array.desserts_info)
        var i = 0

        while (i < titles.size) {
            val image = resources.getIdentifier("dessert$i", "drawable", context?.packageName)
            desserts.add(Desserts(titles[i], dessertsInfo[i], image))
            i++
        }
    }
}