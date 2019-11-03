package com.geekhub.mariia_piastro.hw2.populardesserts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class DessertsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                add(R.id.fragment_container, DessertsListFragment())
                commit()
            }
        }
    }
}
