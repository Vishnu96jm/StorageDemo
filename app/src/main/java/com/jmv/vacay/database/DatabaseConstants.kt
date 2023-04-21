package com.jmv.vacay.database


/*All of these constants represent the columns you have in your database and a statement to
* build a database initially.*/
object DatabaseConstants {

  const val DATABASE_NAME = "Trippey"
  //current version of the database tables and columns
  /*The database version is now 2, meaning your SQLliteHelper will know that the version
  * changed and that you need to update the schema.*/
  const val DATABASE_VERSION = 3

  /**
   * Table names and column names for the database model.
   */

  const val TRIP_TABLE_NAME = "trips"
  const val COLUMN_ID = "id"
  const val COLUMN_TITLE = "title"
  const val COLUMN_COUNTRY = "country"
  const val COLUMN_DETAILS = "details"

  /**
   * Queries to help out with database setup.
   * */

  const val SQL_CREATE_ENTRIES = """
    CREATE TABLE $TRIP_TABLE_NAME
    ($COLUMN_ID TEXT PRIMARY KEY,
     $COLUMN_TITLE TEXT NOT NULL,
     $COLUMN_COUNTRY TEXT NOT NULL DEFAULT '',
     $COLUMN_DETAILS TEXT NOT NULL)
  """

 // const val SQL_CREATE_ENTRIES = "CREATE TABLE $TRIP_TABLE_NAME ($COLUMN_ID TEXT PRIMARY KEY, $COLUMN_TITLE TEXT NOT NULL, $COLUMN_COUNTRY TEXT NOT NULL DEFAULT '', $COLUMN_DETAILS TEXT NOT NULL) "


  const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TRIP_TABLE_NAME"


  const val QUERY_BY_ID = "$COLUMN_ID LIKE ?"
}



