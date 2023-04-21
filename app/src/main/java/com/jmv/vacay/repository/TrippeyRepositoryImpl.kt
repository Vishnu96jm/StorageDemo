

package com.jmv.vacay.repository

import com.jmv.vacay.database.TrippeyDatabase
import com.jmv.vacay.model.Trip


class TrippeyRepositoryImpl(
  private val trippeyDatabase: TrippeyDatabase
  // private val filesHelper: FilesHelper
  // private val gson: Gson
) : TrippeyRepository {



  override fun saveTrip(trip: Trip) = trippeyDatabase.saveTrip(trip)

  override fun deleteTrip(tripId: String) = trippeyDatabase.deleteTrip(tripId)

  override fun getTrips(): List<Trip> = trippeyDatabase.getTrips()




/*  override fun saveTrip(trip: Trip) {
    filesHelper.saveData(trip.id, gson.toJson(trip))
  }

  override fun deleteTrip(tripId: String) {
    filesHelper.deleteData(tripId)
  }
  override fun getTrips(): List<Trip> {
    val tripFiles = filesHelper.getData()

    return tripFiles.map { gson.fromJson(it.readText(), Trip::class.java) }
  }*/

}