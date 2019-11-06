package com.geekhub.mariia_piastro.hw2.populardesserts

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fragment.*


class DessertsListActivity : AppCompatActivity(), DessertAdapter.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragment_container_titles,
                    DessertsListFragment.newInstance(this)
                )
                .commit()
        }
    }

    override fun onItemClick(dessert: Desserts) {
        val fragmentDetails: FrameLayout? = fragment_container_details
        val detailsFragment =
            DessertDetailFragment.newInstance(dessert)
        if (fragmentDetails == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_titles, detailsFragment)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_details, detailsFragment)
                .commit()
        }
    }
}
