package com.geekhub.mariia_piastro.hw2.populardesserts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_desserts_list_fragment.view.*

class DessertsListFragment : Fragment(), DessertAdapter.Callback {

    companion object {
        const val TITLE = "title"
        const val INFO = "info"
        const val IMAGE = "image"
    }

    private val dessertAdapter by lazy { DessertAdapter(desserts, this) }
    private var desserts: ArrayList<Desserts> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(R.layout.activity_desserts_list_fragment, container, false)
        val recyclerView: RecyclerView = view.recyclerView as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        createDessert()
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

    override fun onItemClick(dessert: Desserts) {
        val intent = Intent(activity, DessertDetailActivity::class.java)
        intent.putExtra(TITLE, dessert.title)
        intent.putExtra(INFO, dessert.info)
        intent.putExtra(IMAGE, dessert.image)
        startActivity(intent)
    }
}
