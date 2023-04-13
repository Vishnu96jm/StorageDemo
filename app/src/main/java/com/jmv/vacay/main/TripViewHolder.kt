

package com.jmv.vacay.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jmv.vacay.R
import com.jmv.vacay.model.Trip


class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun showData(trip: Trip, onItemLongTap: (Trip) -> Unit) = with(itemView) {
    itemView.findViewById<TextView>(R.id.tripName).text = trip.title
    itemView.findViewById<TextView>(R.id.tripDescription).text = trip.details

//    tripName.text = trip.title
//    tripDescription.text = trip.details

    setOnLongClickListener {
      onItemLongTap(trip)
      true
    }

  }
}