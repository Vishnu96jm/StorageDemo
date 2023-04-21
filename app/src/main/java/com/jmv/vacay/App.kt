

package com.jmv.vacay

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.jmv.vacay.database.TrippeyDatabase
import com.jmv.vacay.repository.TrippeyRepository
import com.jmv.vacay.repository.TrippeyRepositoryImpl
import java.io.File


class App : Application() {

  companion object {
    private lateinit var instance: App

    private val database by lazy { TrippeyDatabase(instance) }

    val repository: TrippeyRepository by lazy {
      TrippeyRepositoryImpl(
        database
      )
    }
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}