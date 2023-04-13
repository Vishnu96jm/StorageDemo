

package com.jmv.vacay.repository

import com.jmv.vacay.model.Trip

interface TrippeyRepository {

  fun saveTrip(trip: Trip)

  fun deleteTrip(tripId: String)

  fun getTrips(): List<Trip>

}