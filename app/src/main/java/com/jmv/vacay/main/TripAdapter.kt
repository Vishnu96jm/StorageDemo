
package com.jmv.vacay.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmv.vacay.R
import com.jmv.vacay.model.Trip


class TripAdapter(
  private val onItemLongTapped: (Trip) -> Unit
) : RecyclerView.Adapter<TripViewHolder>() {

  private val items = mutableListOf<Trip>()

  fun setData(newItems: List<Trip>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
    holder.showData(items[position], onItemLongTapped)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trip, parent, false)

    return TripViewHolder(view)
  }
}