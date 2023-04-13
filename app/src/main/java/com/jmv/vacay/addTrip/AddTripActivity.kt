

package com.jmv.vacay.addTrip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jmv.vacay.App
import com.jmv.vacay.R
import com.jmv.vacay.databinding.ActivityAddTripBinding
import com.jmv.vacay.model.Trip


class AddTripActivity : AppCompatActivity() {
  private lateinit var binding: ActivityAddTripBinding

  private val repository by lazy { App.repository }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, AddTripActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_trip)

     binding = ActivityAddTripBinding.inflate(layoutInflater)
     val view = binding.root
     setContentView(view)

    initUi()
  }

  private fun initUi() {
    binding.addTrip.setOnClickListener { createTrip() }
  }

  private fun createTrip() {
    val tripTitle = binding.tripTitle.text.toString()
    val tripDescription = binding.tripDescription.text.toString()
    val country = binding.destinationCountry.text.toString()

    if (tripTitle.isNotEmpty() && tripDescription.isNotEmpty() && country.isNotEmpty()) {
      repository.saveTrip(
        Trip(
          title = tripTitle,
          country = country,
          details = tripDescription
        )
      )

      finish()
    }
  }
}