package com.geekhub.mariia_piastro.hw2.populardesserts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DessertDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_titles, DessertDetailFragment())
                .commit()
        }
    }
}
