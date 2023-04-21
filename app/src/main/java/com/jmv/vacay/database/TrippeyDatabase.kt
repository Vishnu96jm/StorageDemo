
package com.jmv.vacay.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.gson.Gson
import com.jmv.vacay.database.DatabaseConstants.DATABASE_NAME
import com.jmv.vacay.database.DatabaseConstants.DATABASE_VERSION
import com.jmv.vacay.database.DatabaseConstants.QUERY_BY_ID
import com.jmv.vacay.database.DatabaseConstants.SQL_CREATE_ENTRIES
import com.jmv.vacay.database.DatabaseConstants.SQL_DELETE_ENTRIES
import com.jmv.vacay.model.Trip


/*
SQLiteOpenHelper
As you'll be implementing a SQL database in Android, you don't have
to resort to writing a ton of SQL. You can instead use the
SQLiteOpenHelper.
The helper is a wrapper around SQL and lets you communicate to the
database and connect its lifecycle with predefined functions and extra
helpers.

SQLiteStoringValues
To store values into the database, use ContentValues
as a key-value map to insert a bunch of values. And to read values,
you just use the Cursor object and read through each column manually. */

class TrippeyDatabase(
  context: Context
  // null for the factory to use the default builder
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

  /*The onCreate() is used to build a database for the first time. You usually want to setup
  * all your tables here and insert test data if you have any.*/
  override fun onCreate(database: SQLiteDatabase?) {
    /*you can use the database to directly insert and execute the SQL.*/
    database?.execSQL(SQL_CREATE_ENTRIES)
  }

  override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      database?.execSQL(SQL_DELETE_ENTRIES)
      onCreate(database)
  }

  fun saveTrip(trip: Trip) {

    val database = writableDatabase

    //Then create a ContentValues object to prepare the data you want to insert.
    //ContentValues are just a map of keys and values to store.
    val newValues = ContentValues().apply {
      put(DatabaseConstants.COLUMN_ID, trip.id)
      put(DatabaseConstants.COLUMN_TITLE, trip.title)
      put(DatabaseConstants.COLUMN_COUNTRY, trip.country)
      put(DatabaseConstants.COLUMN_DETAILS, trip.details)
    }

    /*Finally store the data using the database in the insert function. Ignore the second
    parameter as you don't need it*/
    database.insert(DatabaseConstants.TRIP_TABLE_NAME, null, newValues)
  }

  fun deleteTrip(tripId: String) {
    /*you are writing or changing the database data, so you need to fetch the writeable
    * database.*/
    val database = writableDatabase ?: return

    //selection compares the IDs, and the ID as the argument
    val selection = QUERY_BY_ID
    val selectionArguments = arrayOf(tripId)

    database.delete(DatabaseConstants.TRIP_TABLE_NAME, selection, selectionArguments)
  }

  fun getTrips(): List<Trip> {
    val items = mutableListOf<Trip>()
    val database = readableDatabase ?: return items

    val cursor: Cursor = database.query(
      DatabaseConstants.TRIP_TABLE_NAME,
      null,
      null,
      null,
      null,
      null,
      null
    )

    while (cursor.moveToNext()) {
      items.add(
        Trip(
          cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_ID)),
          cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_TITLE)),
          cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_COUNTRY)),
          cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_DETAILS))
        )
      )
    }

    //close the cursor to free up memory
    cursor.close()
    return items
  }

}