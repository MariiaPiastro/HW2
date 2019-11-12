package com.geekhub.mariia_piastro.hw2.populardesserts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dessert_detail.view.*


class DessertDetailFragment : Fragment() {

    companion object {

        private const val DESSERT = "dessert"

        fun newInstance(desserts: Desserts?): DessertDetailFragment {
            val args = Bundle()
            args.putSerializable(DESSERT, desserts)
            val fragment = DessertDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dessert_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dessert = arguments!!.getSerializable(DESSERT) as Desserts?
        with(view) {
            textViewTitleDessertDetailActivity.text = dessert?.title
            textViewDessertInfoDetailActivity.text = dessert?.info
            imageViewDessertDetailActivity.setImageResource(dessert?.image?: 0)
        }
    }
}