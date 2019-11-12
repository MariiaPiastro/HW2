package com.geekhub.mariia_piastro.hw2.populardesserts

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fragment.*


class DessertsListActivity : AppCompatActivity(), DessertAdapter.Callback {

    private var DESSERT_KEY = "DESSERT"
    private var dessert: Desserts? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container_titles,
                    DessertsListFragment.newInstance()
                )
                .commit()
        }
        else dessert = savedInstanceState.getSerializable(DESSERT_KEY) as Desserts?

        if (fragment_container_details != null) {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStack()
            if (dessert == null)
                dessert = Desserts("Десерт не выбран", "", R.drawable.empty_image)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_titles, DessertsListFragment())
                .replace(R.id.fragment_container_details, DessertDetailFragment.newInstance(dessert))
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(DESSERT_KEY, dessert)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        dessert = savedInstanceState?.getSerializable(DESSERT_KEY) as Desserts?
    }

    override fun onItemClick(dessert: Desserts) {
        this.dessert = dessert
        val fragmentDetails: FrameLayout? = fragment_container_details
        val detailsFragment =
            DessertDetailFragment.newInstance(dessert)
        if (fragmentDetails == null) { //портрет
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_titles, detailsFragment)
                .addToBackStack(null)
                .commit()
        } else { //пейзаж
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStack()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_details, detailsFragment)
                .commit()
        }
    }
}
