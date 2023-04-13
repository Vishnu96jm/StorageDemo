

package com.jmv.vacay.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import com.jmv.vacay.files.FilesHelper
import com.jmv.vacay.model.Trip


class TrippeyRepositoryImpl(
  private val filesHelper: FilesHelper,
  /*Gson - used to serialize and de-serialize data, or to convert Kotlin objects to a string
representation of a Gson structure and from the String back to the Kotlin object.*/
  private val gson: Gson
) : TrippeyRepository {


  override fun saveTrip(trip: Trip) {
    filesHelper.saveData(trip.id, gson.toJson(trip))
  }

  override fun deleteTrip(tripId: String) {
    filesHelper.deleteData(tripId)
  }

  /*use filesHelper to fetch all the files, then you map each file to a trip by reading its
  * text and parsing it using gson.*/
  override fun getTrips(): List<Trip> {
    val tripFiles = filesHelper.getData()

    return tripFiles.map { gson.fromJson(it.readText(), Trip::class.java) }
  }

}