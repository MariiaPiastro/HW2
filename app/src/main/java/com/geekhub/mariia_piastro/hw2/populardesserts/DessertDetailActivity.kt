package com.geekhub.mariia_piastro.hw2.populardesserts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class DessertDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dessert_detail)

        val fragment: DessertDetailFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as DessertDetailFragment
    }
}
