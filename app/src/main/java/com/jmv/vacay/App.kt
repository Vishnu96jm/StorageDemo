

package com.jmv.vacay

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.jmv.vacay.files.FilesHelperImpl
import com.jmv.vacay.repository.TrippeyRepository
import com.jmv.vacay.repository.TrippeyRepositoryImpl
import java.io.File


class App : Application() {

  companion object {
    private lateinit var instance: App

  //  private val filesHelper by lazy { FilesHelperImpl(instance.filesDir) }
    private val gson by lazy { Gson() }

    /*Instead of using the internal storage directory, now you're using the file building
    * function.*/
    /////////////////////
    private val filesHelper by lazy { FilesHelperImpl(getFilesDirectory()) }

    /*This function will build the external storage directory. It will fetch the reference
    * of the file and check if it exists.
    *
    * Access the stored file: SDcard -> Android -> data -> package name -> files
    * They're stored within a different directory now, and other apps can look them up if you
    * give them the permission. Really useful when you want to share data between different
    * applications.*/
    private fun getFilesDirectory(): File {
      val directory = File(instance.getExternalFilesDir(null), "")

      if (!directory.exists()) {
        //create a directory and return it
        directory.mkdirs()
      }
      return directory
    }

    val repository: TrippeyRepository by lazy {
      TrippeyRepositoryImpl(
        filesHelper,
        gson
      )
    }
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}