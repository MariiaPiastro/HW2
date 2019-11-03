package com.geekhub.mariia_piastro.hw2.populardesserts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dessert_detail.view.*


class DessertDetailFragment : Fragment() {

    private lateinit var desserts: Desserts

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_dessert_detail, container, false)
        view.textViewTitleDessertDetailActivity.text = activity?.intent?.getStringExtra(DessertsListFragment.TITLE)
        view.textViewDessertInfoDetailActivity.text = activity?.intent?.getStringExtra(DessertsListFragment.INFO)
        val image = activity?.intent?.getIntExtra(DessertsListFragment.IMAGE,0)?: 0
        view.imageViewDessertDetailActivity.setImageResource(image)
        return view
    }

}