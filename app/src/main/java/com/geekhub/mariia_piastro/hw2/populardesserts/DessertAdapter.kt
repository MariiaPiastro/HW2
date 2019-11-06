package com.geekhub.mariia_piastro.hw2.populardesserts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class DessertAdapter(
    private val desserts: List<Desserts>
) :
    RecyclerView.Adapter<DessertAdapter.ViewHolder>() {

    interface Callback {
        fun onItemClick(dessert: Desserts)
    }
    private var callback: Callback? = null

    fun setCallback (callback: Callback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return desserts.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(desserts[p1])
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                callback?.onItemClick(desserts[adapterPosition])
            }
        }

        fun bind(desserts: Desserts) {
            view.TextViewTitleDessert.text = desserts.title
            view.imageViewDessert.setImageResource(desserts.image)
        }
    }
}