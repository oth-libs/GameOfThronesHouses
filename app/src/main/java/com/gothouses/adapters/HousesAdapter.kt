package com.gothouses.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gothouses.models.House


class HousesAdapter(val houses: List<House>, val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<HousesAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: House)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(com.gothouses.R.layout.house_recycler_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount() = houses.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = houses[position].name
        holder.itemView.setOnClickListener { onItemClickListener.onItemClick(houses[position]) }
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(com.gothouses.R.id.name)
    }

}