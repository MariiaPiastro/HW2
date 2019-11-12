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
    private lateinit var mCallback: DessertAdapter.Callback

    companion object {
        fun newInstance(): DessertsListFragment = DessertsListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallback = context as DessertAdapter.Callback
        createDessert()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.activity_desserts_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view.recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = dessertAdapter.apply {
                callback = mCallback
            }
        }
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