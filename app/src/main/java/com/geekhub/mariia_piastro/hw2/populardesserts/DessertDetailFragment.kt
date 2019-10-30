package com.geekhub.mariia_piastro.hw2.populardesserts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class DessertDetailFragment : Fragment() {

    private lateinit var desserts: Desserts
    private lateinit var textViewTitleDessertDetailActivity: TextView
    private lateinit var textViewDessertInfoDetailActivity: TextView
    private lateinit var imageViewDessertDetailActivity: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_dessert_detail, container, false)
        textViewDessertInfoDetailActivity = v.findViewById(R.id.textViewDessertInfoDetailActivity)
        textViewTitleDessertDetailActivity = v.findViewById(R.id.textViewTitleDessertDetailActivity)
        imageViewDessertDetailActivity = v.findViewById(R.id.imageViewDessertDetailActivity)
        return v
    }

}